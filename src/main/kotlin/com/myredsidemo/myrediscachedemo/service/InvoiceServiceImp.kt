package com.myredsidemo.myrediscachedemo.service

import com.myredsidemo.myrediscachedemo.entity.Invoice
import com.myredsidemo.myrediscachedemo.exception.InvoiceNotFoundException
import com.myredsidemo.myrediscachedemo.repository.InvoiceRepository
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service


@Service
class InvoiceServiceImp : InvoiceService {

    @Autowired
    lateinit var invoiceRepository: InvoiceRepository


    override fun saveInvoice(invoice: Invoice): Invoice {
        return invoiceRepository.save(invoice)
    }

    override fun deleteInvoice(invoiceId: Int) {
        try {
            val voice = invoiceRepository.findById(invoiceId)
            invoiceRepository.delete(voice.get())
        } catch (e: InvoiceNotFoundException) {
            throw InvoiceNotFoundException("Invoice not found")
        }
    }

//    @Modifying(clearAutomatically = true, flushAutomatically = true)
//    override fun updateInvoice(invoice: Invoice, invoiceId: Int): Invoice {
//        try {
//            val voice = invoiceRepository.findById(invoiceId)
//            voice.get().invName = invoice.invName
//            voice.get().invAmount = invoice.invAmount
//            return invoiceRepository.save(voice.get())
//        } catch (e: InvoiceNotFoundException) {
//            throw InvoiceNotFoundException("Invoice not found")
//        }
//    }

    override fun updateInvoice(invoice: Invoice, invoiceId: Int): Int {
        val name: String = invoice.invName
        val amount: Double = invoice.invAmount
        return invoiceRepository.updateInvoice(invoiceId,name,amount)

    }


    override fun getInvoice(invoiceId: Int): Invoice {
        return try {
            invoiceRepository.findById(invoiceId).get()
        } catch (e: InvoiceNotFoundException) {
            throw InvoiceNotFoundException("Not found")
        }
    }


    override fun getAllInvoice(): List<Invoice> {
        return try {
            invoiceRepository.findAll()
        } catch (e: InvoiceNotFoundException) {
            throw InvoiceNotFoundException("Not found")
        }
    }

    @Autowired
    lateinit var redisson: RedissonClient

    override fun testLock(): String? {
        // 1.获取锁，只要锁的名字一样，获取到的锁就是同一把锁。
        val lock: RLock = redisson.getLock("WuKong-lock")

        // 2.加锁
        lock.lock()
        try {
            println("加锁成功，执行后续代码。线程 ID：" + Thread.currentThread().id)
            Thread.sleep(10000)
        } catch (e: Exception) {
            //TODO
        } finally {
            lock.unlock()
            // 3.解锁
            println("Finally，释放锁成功。线程 ID：" + Thread.currentThread().id)
        }
        return "test lock ok"
    }
}
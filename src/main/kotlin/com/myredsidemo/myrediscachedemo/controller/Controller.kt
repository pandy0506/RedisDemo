package com.myredsidemo.myrediscachedemo.controller

import com.myredsidemo.myrediscachedemo.entity.Invoice
import com.myredsidemo.myrediscachedemo.service.InvoiceService
import com.myredsidemo.myrediscachedemo.utils.MyTimerTask
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock


@RestController
@RequestMapping("/voice")
class Controller {

    @Autowired
    lateinit var invoiceService: InvoiceService
    @Autowired
    lateinit var redisson:RedissonClient

    val lock = ReentrantLock()


    @GetMapping
    fun getAllVoice(): List<Invoice> {
        return invoiceService.getAllInvoice()
    }

    @GetMapping("/{id}")
    fun getVoice(@PathVariable id: Int): Invoice {
        lock.withLock {
            for (i in 1..40) {
//                Thread.sleep(1000)
                println(invoiceService.getInvoice(id).invName)
            }
        }
        return invoiceService.getInvoice(id)
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveVoice(@RequestBody invoice: Invoice): Invoice {
        return invoiceService.saveInvoice(invoice)
    }

    @PostMapping("/update/{id}")
    fun updateVoice(@RequestBody invoice: Invoice, @PathVariable id: Int):Int {
        lock.withLock {
            return invoiceService.updateInvoice(invoice, id)
        }
    }
}
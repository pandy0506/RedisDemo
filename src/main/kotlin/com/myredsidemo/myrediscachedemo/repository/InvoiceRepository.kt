package com.myredsidemo.myrediscachedemo.repository

import com.myredsidemo.myrediscachedemo.entity.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityManager


@Repository
interface InvoiceRepository:JpaRepository<Invoice, Int>{



    @Modifying(clearAutomatically = true, flushAutomatically = true)
    override fun findById(id: Int): Optional<Invoice> {
        val invoice = findById(id)
        return invoice
    }

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Invoice i set i.invName = ?2, i.invAmount = ?3 where i.invId = ?1")
    fun updateInvoice(invoiceId: Int, name: String, amount: Double): Int {
        return invoiceId
    }
}
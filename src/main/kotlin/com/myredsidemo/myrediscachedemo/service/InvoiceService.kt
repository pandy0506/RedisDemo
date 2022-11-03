package com.myredsidemo.myrediscachedemo.service

import com.myredsidemo.myrediscachedemo.entity.Invoice


interface InvoiceService {
    fun saveInvoice(invoice: Invoice): Invoice
    fun deleteInvoice(invoiceId: Int)
    fun updateInvoice(invoice: Invoice, invoiceId: Int): Int
    fun getInvoice(invoiceId: Int): Invoice

    fun getAllInvoice(): List<Invoice>
    fun testLock():String?
}
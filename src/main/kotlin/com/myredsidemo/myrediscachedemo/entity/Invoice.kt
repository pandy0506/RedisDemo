package com.myredsidemo.myrediscachedemo.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
class Invoice: Serializable {

    companion object {
        val serialVersionUID: Long = -4439114469417994311L
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var invId: Int = 0
    lateinit var invName: String
    var invAmount: Double = 0.0

}
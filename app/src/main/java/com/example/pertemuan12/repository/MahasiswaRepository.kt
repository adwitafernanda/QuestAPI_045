package com.example.pertemuan12.repository

import com.example.pertemuan12.model.Mahasiswa
import com.example.pertemuan12.service.MahasiswaService
import okio.IOException

interface  MahasiswaRepository{
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)
    suspend fun getMahasiswa(): List<Mahasiswa>
    suspend fun updateMahasiswa(nim:String,mahasiswa:Mahasiswa)
    suspend fun deleteMahasiswa(nim:String)
    suspend fun getMahasiswabynim(nim:String):Mahasiswa
}

class NetworkKontakRepository(
    private val kontakApiService: MahasiswaService
):MahasiswaRepository {
    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        kontakApiService.insertMahasiswa((mahasiswa))
    }

    override suspend fun getMahasiswa(): List<Mahasiswa> =
        kontakApiService.getAllMahasiswa()


    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        kontakApiService.updateMahasiswa(nim,mahasiswa)
    }

    override suspend fun deleteMahasiswa(nim: String) {
        try {
            val response = kontakApiService.deleteMahasiswa(nim)
            if (!response.isSuccessful){
                throw IOException("Failed to delete kontak. HTTP Status code:"+
                "${response.code()}")
            }else {
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getMahasiswabynim(nim: String): Mahasiswa {
        return kontakApiService.getMahasiswabyNim(nim)
    }
}
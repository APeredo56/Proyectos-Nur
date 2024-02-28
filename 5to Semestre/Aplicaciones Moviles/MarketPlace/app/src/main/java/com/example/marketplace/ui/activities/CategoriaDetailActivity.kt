package com.example.marketplace.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.marketplace.databinding.ActivityCategoriaDetailBinding
import com.example.marketplace.dal.conn.AppDatabase
import com.example.marketplace.dal.dto.Categoria
import java.time.Instant
import java.time.format.DateTimeFormatter

class CategoriaDetailActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    lateinit var binding: ActivityCategoriaDetailBinding
    private var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.getInstance(this)
        id = intent.getIntExtra("id", 0)
        if (id != 0) {
            loadCategoriaDetail()
            binding.txtCategoryId.isClickable = false
        } else {
            binding.txtCategoryId.visibility = View.INVISIBLE
        }
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnCancelCategory.setOnClickListener { finish() }
        binding.btnSaveCategory.setOnClickListener { saveCategoria() }
    }

    private fun saveCategoria() {
        val nombres = binding.txtCategoryName.editText?.text.toString()
        val createdAt = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        val categoria = Categoria(
            nombres, createdAt, createdAt
        )
        if (id == 0) {
            db.categoriaDao().insert(
                categoria
            )
        } else {
            categoria.id = id
            db.categoriaDao().update(
                categoria
            )
        }
        finish()
    }

    private fun loadCategoriaDetail() {
        val categoria = db.categoriaDao().getById(id)
        if (categoria == null) {
            finish()
            return
        }
        loadCategoriaInForm(categoria)
    }

    private fun loadCategoriaInForm(categoria: Categoria) {
        binding.txtCategoryId.editText?.setText(categoria.id.toString())
        binding.txtCategoryName.editText?.setText(categoria.nombre)
    }
}
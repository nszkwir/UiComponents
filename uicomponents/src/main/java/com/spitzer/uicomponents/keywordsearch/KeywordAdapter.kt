package com.spitzer.uicomponents.keywordsearch

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spitzer.uicomponents.R
import java.util.*
import java.util.UUID.randomUUID

class KeywordAdapter(
    private val drawableStateColor: ColorStateList,
    private val backgroundStateColor: ColorStateList
) : RecyclerView.Adapter<KeywordAdapter.ViewHolder>() {

    private var keywordsList: ArrayList<KeywordDataClass> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.keyword_item, parent, false)
        )
    }

    override fun getItemCount() = keywordsList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(keywordsList[position])
    }

    // TODO improve validations
    fun addKeyword(keyword: String): Boolean {
        return if (keywordsList.size < MAX_KEYWORDS_ALLOWED) {
            keywordsList.add(
                KeywordDataClass(
                    randomUUID(),
                    keyword
                )
            )
            notifyItemInserted(keywordsList.size)
            true
        } else {
            false
        }
    }

    fun removeKeyword(keyword: KeywordDataClass) {
        val position = keywordsList.indexOfFirst { it.id == keyword.id }
        if (position > -1 ) {
            keywordsList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getKeywordsString(separator: String = " ") = keywordsList.joinToString(separator = separator)

    inner class KeywordDataClass(
        val id: UUID,
        val keyword: String
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(keyword: KeywordDataClass) {
            itemView.backgroundTintList = backgroundStateColor
            val keywordText = itemView.findViewById<TextView>(R.id.text_keyword)
            val removeImageView = itemView.findViewById<ImageView>(R.id.delete_keyword)
            keywordText.text = keyword.keyword
            removeImageView.apply {
                imageTintList = drawableStateColor
                setOnClickListener {
                    removeKeyword(keyword)
                }
            }
        }
    }

    companion object {
        const val MAX_KEYWORDS_ALLOWED = 5
    }
}

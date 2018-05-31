package com.example.internhbaoquoc.firebase_kotlin.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.SimpleTarget
import com.example.internhbaoquoc.firebase_kotlin.R
import com.example.internhbaoquoc.firebase_kotlin.activity.CommentActivity
import com.example.internhbaoquoc.firebase_kotlin.model.StatusDataModel
import com.example.internhbaoquoc.firebase_kotlin.utils.MyConstants
import com.example.internhbaoquoc.firebase_kotlin.utils.TinhGio
import com.example.internhbaoquoc.firebase_kotlin.utils.Upload
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.item_status.view.*
import java.lang.Exception

class AdapterStatus(val ctx: Context, val listData: ArrayList<StatusDataModel>) : RecyclerView.Adapter<AdapterStatus.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view = LayoutInflater.from(ctx).inflate(R.layout.item_status, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    companion object {
        var liked: Boolean=false
    }
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        (holder).bindData(listData.get(position))

        holder.itemView.txtOption.setOnClickListener {
            Upload.deleteStatus(MyConstants.STATUS_PUBLISH, listData.get(position).getId())
            notifyDataSetChanged()
        }

        holder.itemView.btnLike.setOnClickListener {
            var obj =listData.get(position)
            if(!liked)
            {
                obj.setNumLike(obj.getNumLike()+1)
                Upload.increaseLike(MyConstants.STATUS_PUBLISH,obj.getId(),obj)
                notifyDataSetChanged()
                holder.itemView.btnLike.setImageResource(R.drawable.ic_like_32_liked)
                liked=!liked
            }
            else
            {
                obj.setNumLike(obj.getNumLike()-1)
                Upload.increaseLike(MyConstants.STATUS_PUBLISH,obj.getId(),obj)
                notifyDataSetChanged()
                holder.itemView.btnLike.setImageResource(R.drawable.ic_like_32)
                liked=!liked
            }
        }
        holder.itemView.btnCmt.setOnClickListener {
            val it:Intent = Intent(ctx,CommentActivity::class.java)
            Log.e(MyConstants.TAG,"idSTT send qua commentAct ${listData.get(position).getId()}")
            it.putExtra("id",listData.get(position).getId())
            ctx.startActivity(it)
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(stt: StatusDataModel) {
            itemView.txtName.text = stt.getName()
            itemView.txtHour.text = TinhGio.calc(stt.getHour())
            itemView.txtContentText.text = stt.getContentText()
            itemView.txtNumLike.text = stt.getNumLike().toString() + " likes"
            itemView.txtNumCmt.text = stt.getNumCmt().toString() + " comments"

            Picasso.get().load(stt.getAvata())
                    .into(itemView.imgAvarta, object : Callback {
                        override fun onSuccess() {
                            itemView.progressbarAVT.visibility = View.GONE
                        }

                        override fun onError(e: Exception?) {
                            itemView.imgAvarta.setImageResource(R.drawable.no_avarta)
                        }

                    })
            Picasso.get().load(stt.getContentImage())
                    .into(itemView.imgContentImage, object : Callback {
                        override fun onSuccess() {
                            itemView.progressbarImageContent.visibility = View.GONE
                        }

                        override fun onError(e: Exception?) {
                            itemView.imgContentImage.setImageResource(R.drawable.no_image)
                        }

                    })
        }
    }
}
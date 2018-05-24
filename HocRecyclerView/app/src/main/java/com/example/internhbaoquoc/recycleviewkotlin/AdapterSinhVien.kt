package com.example.internhbaoquoc.recycleviewkotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.item_view.view.*

class AdapterSinhVien(var contex: Context, var listData: ArrayList<SinhVien>) : RecyclerView.Adapter<AdapterSinhVien.MyHolder>(),recyclerViewItemClick{

    private var callDialog: initDialogAtActivity?=null

    fun setListenerDialog(clickListener: initDialogAtActivity) {
        callDialog = clickListener
    }

    //click long tại mỗi item sẽ show dialog bên mainactivity bằng interface initinitDialogAtActivity
    override fun onItemLongClick(position: Int, v: View, isLongCLick: Boolean) {
        Toast.makeText(contex,"Long click tại item thứ: $position",Toast.LENGTH_SHORT).show()
        callDialog?.openDialog(position,listData.get(position))
    }



    fun addItem(sv: SinhVien) {
        listData.add(sv)
        notifyItemInserted(listData.size)
        notifyDataSetChanged()
    }


    fun removeAt(pos: Int) {
        listData.removeAt(pos)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var v = LayoutInflater.from(contex).inflate(R.layout.item_view, parent, false)
        return MyHolder(v)
    }


    override fun getItemCount(): Int {
        return listData.size
    }

    //gán và làm việc với mỗi view trong holder
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        (holder).bindData(listData[position])
        holder.itemView.btnItemDelete.setOnClickListener {
            Toast.makeText(contex, "Xoa item thu " + position, Toast.LENGTH_SHORT).show()
            removeAt(position)
        }
        holder.setListener(this)
    }

    //Đây là view của 1 item, trong view này sẽ có các thành phần widget con như button, textview
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) ,View.OnLongClickListener{


        private var listener: recyclerViewItemClick? = null

        fun setListener(clickListener: recyclerViewItemClick) {
            listener = clickListener
        }

        override fun onLongClick(v: View?): Boolean {
            if (v != null) {
                listener?.onItemLongClick(adapterPosition,v,true)
            }
            return true
        }

        fun bindData(sv: SinhVien) {
            itemView.imgItem.setBackgroundResource(sv.image)
            itemView.txtItemName.setText(sv.name)
            itemView.txtItemAddress.setText(sv.address)
            itemView.setOnLongClickListener(this)
        }

    }
}


//https://viblo.asia/p/recyclerview-trong-android-lA7GKwbWGKZQ
//https://viblo.asia/p/recyclerview-trong-android-phan-2-p1PvQ5W9vldr

/*class Holder tạo 1 interface và setListener và thực hiện chuyển data qua cho class bự
là AdapterSinhVien (AdapterSinhVien chỉ việc implement lại và xài các data)
trong Viewholder class cần có 1 thể hiện interface và 1 setter cho interface này,do 1 itemView ta cần onClick cho nó nên
ta lấy itemView.setOnLick các thứ và override lại phương thức và trong phương thức này ta gọi thể hiện của interface.phương thức
từ AdaptyerSinhVien implement lại và override,holder trong Bindata setter cái interface kia

*/
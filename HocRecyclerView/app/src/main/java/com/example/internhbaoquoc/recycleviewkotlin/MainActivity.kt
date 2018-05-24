package com.example.internhbaoquoc.recycleviewkotlin

import android.app.Dialog
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_them_sinhvien.*
import kotlinx.android.synthetic.main.dialog_update_sinhvien.*
import kotlinx.android.synthetic.main.item_view.*

class MainActivity : AppCompatActivity(), initDialogAtActivity {
    var lists: ArrayList<SinhVien>? = null
    val TAG: String = "HuynhBaoQuoc"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var lists : ArrayList<SinhVien> = ArrayList()
        lists = ArrayList()
        lists!!.add(SinhVien(R.drawable.ic1, "Quoc 1", "An Giang 1"))
        lists!!.add(SinhVien(R.drawable.ic2, "Quoc 2", "An Giang 2"))
        lists!!.add(SinhVien(R.drawable.ic3, "Quoc 3", "An Giang 3"))
        lists!!.add(SinhVien(R.drawable.ic4, "Quoc 4", "An Giang 4"))
        lists!!.add(SinhVien(R.drawable.ic5, "Quoc 5", "An Giang 5"))


        recycleView2.layoutManager = LinearLayoutManager(this)
        var adapter2 = AdapterSinhVien(applicationContext, lists!!)
        adapter2.setListenerDialog(this)
        recycleView2.adapter = adapter2
        Log.e(TAG, lists!!.toString())

        btnThem.setOnClickListener {
            var d: Dialog = Dialog(this)
            d.setContentView(R.layout.dialog_them_sinhvien)
            d.setTitle("Thêm sinh viên")
            d.show()

            d.btnDialogOkThem.setOnClickListener {
                adapter2.addItem(SinhVien(R.drawable.ic_launcher_background,d.edtDialogNameThem.text.toString(),d.edtDialogAddrThem.text.toString()))
                d.cancel()
            }
        }
    }

    /*
    * override lại trong interface initDialogAtActivity
    *
    * trong đây khởi tạo hiển thị dialog với data cũ nên cần truyền vào position và SinhVien dialog.edtDialogAddr.setText(get.address) ..dialog.edtDialogName.setText(get.name)
    * list.set(position,Object) thay đổi phần tử thứ posioton = 1 phần tử mới
    * */
    override fun openDialog(position: Int, get: SinhVien) {
        //init dialog
        var dialog: Dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_update_sinhvien)
        dialog.setTitle("Cập nhật sinh viên")
        dialog.show()
        //gán data cũ vào edittext
        dialog.edtDialogAddr.setText(get.address)
        dialog.edtDialogName.setText(get.name)

        //cancel dialog khi bấm cancel
        dialog.btnDialogCancel.setOnClickListener { view -> dialog.dismiss() }
        dialog.btnDialogUpdate.setOnClickListener {
            //modify dữ liệu tại position của list
            view ->
            lists!!.set(position, SinhVien(get.image,dialog.edtDialogName.text.toString(), dialog.edtDialogAddr.text.toString()))
            Log.e(TAG, dialog.edtDialogName.text.toString() + " - " + dialog.edtDialogAddr.text.toString())
            //gán lại adapter, tín hiệu thay đổi
            recycleView2.adapter = AdapterSinhVien(applicationContext, lists!!)
            recycleView2.adapter.notifyItemInserted(position)
            recycleView2.adapter.notifyDataSetChanged()
            dialog.dismiss()

            Log.e(TAG, lists!!.toString())
        }
    }
}

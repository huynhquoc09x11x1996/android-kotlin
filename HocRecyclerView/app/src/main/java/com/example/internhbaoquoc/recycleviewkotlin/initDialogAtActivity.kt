package com.example.internhbaoquoc.recycleviewkotlin


/*
* interface khi long click 1 item thì gửi tín hiệu đến activity, activity sẽ mởi dialog và interface này có phương thức
* openDialog:
* @param position: gửi position để list data bên activity sẽ bik cần update vị trí nào
* @param get: gởi Object thứ position qua activity tiến hành update
*
* */
interface initDialogAtActivity {

    fun openDialog(position: Int, get: SinhVien)
}
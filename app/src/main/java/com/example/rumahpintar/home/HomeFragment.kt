package com.example.rumahpintar.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rumahpintar.R
import com.example.rumahpintar.home.detail.DetailMatematikaActivity
import com.example.rumahpintar.home.model.Video
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.collections.ArrayList


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var listVideo: ArrayList<Video> = arrayListOf()
//    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val vid1 = Video(
            "English",
            "https://www.youtube.com/watch?v=teMcwnplFv4",
            "https://img.youtube.com/vi/teMcwnplFv4/hqdefault.jpg"
        )
        val vid2 = Video(
            "Indonesia",
            "https://www.youtube.com/watch?v=m-IH3st1poE",
            "https://img.youtube.com/vi/m-IH3st1poE/hqdefault.jpg"
        )
        rv_rekomendasi.layoutManager = GridLayoutManager(context, 2)
        val gridVideoAdapter = GridVideoAdapter()

        listVideo.add(vid1)
        listVideo.add(vid2)
        rv_rekomendasi.adapter = gridVideoAdapter
        setOnClickItem(gridVideoAdapter)
        gridVideoAdapter.addToListAdapter(listVideo)
        gridVideoAdapter.notifyDataSetChanged()

        img_math.setOnClickListener {
            startActivity(Intent(context, DetailActivity::class.java))
        }

        cv_matematika.setOnClickListener{
            startActivity(Intent(context, DetailMatematikaActivity::class.java))
        }

//        preferences = requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        getUsername()

//        val name = preferences.getString("NAME", "")
//        textNama.text = name
    }

    private fun getUsername() {
        val databaseReference = FirebaseDatabase.getInstance().reference.child("user").child(
            FirebaseAuth.getInstance().uid.toString()
        )




        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val nama: String =
                    Objects.requireNonNull(dataSnapshot.child("namaUser").value).toString()
                textNama.text = nama
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

    }

    private fun setOnClickItem(listVideoAdapter: GridVideoAdapter) {
        listVideoAdapter.setOnItemClickCallback(object : GridVideoAdapter.OnItemClickCallback {
            override fun onItemClick(data: Video) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data.linkVideo)))
            }
        })
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
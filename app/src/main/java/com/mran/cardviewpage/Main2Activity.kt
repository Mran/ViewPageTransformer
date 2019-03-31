package com.mran.cardviewpage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.startActivity

class Main2Activity : AppCompatActivity(), AdapterView.OnItemClickListener {
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        BookFlippageFadePageTransormer.setOnClickListener {
            startActivity<MainActivity>(Pair("PageTransormer", "com.mran.cardviewpage.BookFlippageFadePageTransormer"))
        }
        CardFlipoverPageTransormer.setOnClickListener {
            startActivity<MainActivity>(Pair("PageTransormer", "com.mran.cardviewpage.CardFlipoverPageTransormer"))
        }
        CardStackPaegTransformer.setOnClickListener {
            startActivity<MainActivity>(Pair("PageTransormer", "com.mran.cardviewpage.CardStackPaegTransformer"))
        }
        CascadeZoomPageTransformer.setOnClickListener {
            startActivity<MainActivity>(Pair("PageTransormer", "com.mran.cardviewpage.CascadeZoomPageTransformer"))
        }
        CubesPageTransformer.setOnClickListener {
            startActivity<MainActivity>(Pair("PageTransormer", "com.mran.cardviewpage.CubesPageTransformer"))
        }
        DepthCardTransformer.setOnClickListener {
            startActivity<MainActivity>(Pair("PageTransormer", "com.mran.cardviewpage.DepthCardTransformer"))
        }
        FilpPageRotationPageTransformer.setOnClickListener {
            startActivity<MainActivity>(Pair("PageTransormer", "com.mran.cardviewpage.FilpPageRotationPageTransformer"))
        }
        TurntablePageTransformer.setOnClickListener {
            startActivity<MainActivity>(Pair("PageTransormer", "com.mran.cardviewpage.TurntablePageTransformer"))
        }
        ZoominPagerTransFormer.setOnClickListener {
            startActivity<MainActivity>(Pair("PageTransormer", "com.mran.cardviewpage.ZoominPagerTransFormer"))
        }
    }
}

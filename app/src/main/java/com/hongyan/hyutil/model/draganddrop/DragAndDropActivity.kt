package com.hongyan.hyutil.model.draganddrop

import android.animation.ValueAnimator
import android.graphics.Color
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hongyan.hyutil.R
import com.hongyan.hyutil.mvp.MVPBaseActivity
import com.hongyan.hyutil.utils.T
import kotlinx.android.synthetic.main.activity_draganddrop.*

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
class DragAndDropActivity : MVPBaseActivity<DragAndDropContract.View?, DragAndDropPresenter?>(), DragAndDropContract.View {
    override fun setLayoutId(): Int = R.layout.activity_draganddrop

    override fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {

        mRecyclerView.apply {
            layoutManager = GridLayoutManager(this@DragAndDropActivity, 2)
            adapter = mAdapter
        }
        mAdapter.setList(arrayListOf<Int>(0, 1, 2, 3, 4, 5))
        mAdapter.draggableModule.isDragEnabled = true
        mAdapter.draggableModule.itemTouchHelperCallback.setSwipeMoveFlags(ItemTouchHelper.START or ItemTouchHelper.END)
        mAdapter.draggableModule.setOnItemDragListener(object : OnItemDragListener {
            override fun onItemDragMoving(source: RecyclerView.ViewHolder?, from: Int, target: RecyclerView.ViewHolder?, to: Int) {
            }

            override fun onItemDragStart(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
                val holder = viewHolder as BaseViewHolder
                // 开始时，item背景色变化，demo这里使用了一个动画渐变，使得自然
                val startColor = Color.WHITE
                val endColor = Color.rgb(245, 245, 245)
                val v = ValueAnimator.ofArgb(startColor, endColor)
                v.addUpdateListener { animation: ValueAnimator -> holder.itemView.setBackgroundColor(animation.animatedValue as Int) }
                v.duration = 300
                v.start()
            }

            override fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
                val holder = viewHolder as BaseViewHolder
                // 结束时，item背景色变化，demo这里使用了一个动画渐变，使得自然
                val startColor = Color.rgb(245, 245, 245)
                val endColor = Color.WHITE
                val v = ValueAnimator.ofArgb(startColor, endColor)
                v.addUpdateListener { animation -> holder.itemView.setBackgroundColor(animation.animatedValue as Int) }
                v.duration = 300
                v.start()
            }

        })
        mAdapter.setOnItemClickListener { adapter, view, position ->
            T.show("当前点击 $position")
        }
    }

    private val mAdapter by lazy {
        object : BaseQuickAdapter<Int, BaseViewHolder>(R.layout.adapter_draganddrop), DraggableModule {
            override fun convert(holder: BaseViewHolder, item: Int) {
                holder.setText(R.id.tvContent, "item${item}")
            }
        }
    }
}
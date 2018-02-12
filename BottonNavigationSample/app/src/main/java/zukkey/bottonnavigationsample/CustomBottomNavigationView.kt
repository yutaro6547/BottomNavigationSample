package zukkey.bottonnavigationsample

import android.annotation.SuppressLint
import android.content.Context
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.AttributeSet
import android.util.Log




/**
 * CustomBottomNavigationView Class
 */
class CustomBottomNavigationView : BottomNavigationView {

  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    disableShiftMode()
    disableSmallLabel()
    //showSmallLabel()
  }

  private fun disableShiftMode() {
    val menuView = getBottomMenuView()!!
    try {
      val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
      shiftingMode.isAccessible = true
      shiftingMode.setBoolean(menuView, false)
      shiftingMode.isAccessible = false
    } catch (e: NoSuchFieldException) {
      Log.e("FieldError", e.message)
    } catch (e: IllegalAccessException) {
      Log.e("IllegalAccessError", e.message)
    }
  }

  @SuppressLint("RestrictedApi")
  private fun disableSmallLabel() {
    val menuView = getBottomMenuView()!!
    try {
      for (i in 0 until menuView.childCount) {
        val itemView = menuView.getChildAt(i) as BottomNavigationItemView
        itemView.setShiftingMode(true)
        itemView.setChecked(false)
      }
    } catch (e: NoSuchFieldException) {
      Log.e("FieldError", e.message)
    } catch (e: IllegalAccessException) {
      Log.e("IllegalAccessError", e.message)
    }
  }

  @SuppressLint("RestrictedApi")
  private fun showSmallLabel() {
    val menuView = getBottomMenuView()!!
    try {
      for (i in 0 until menuView.childCount) {
        val itemView = menuView.getChildAt(i) as BottomNavigationItemView
        itemView.setShiftingMode(false)
        itemView.setChecked(false)
      }
    } catch (e: NoSuchFieldException) {
      Log.e("FieldError", e.message)
    } catch (e: IllegalAccessException) {
      Log.e("IllegalAccessError", e.message)
    }
  }

  private fun getBottomMenuView(): BottomNavigationMenuView? {
    var menuView: Any? = null
    try {
      val field = BottomNavigationView::class.java.getDeclaredField("mMenuView")
      field.isAccessible = true
      menuView = field.get(this)
    } catch (e: NoSuchFieldException) {
      Log.e("FieldError", e.message)
    } catch (e: IllegalAccessException) {
      Log.e("IllegalAccessError", e.message)
    }
    return menuView as BottomNavigationMenuView?
  }
}
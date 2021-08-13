package works.anilo.arief.presentation.main.contributors.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessScrollListener(
        private val layoutManager: LinearLayoutManager?,
        private val onLoadMore: (Int) -> Unit
) : RecyclerView.OnScrollListener() {

    companion object {
        private const val COLUMN_COUNT = 1
        private const val LOAD_MORE_TYPE = 0
    }

    //the total number of items in data set after the last load
    private var previousTotal = 0

    //true if we are still waiting for the last set of data to load
    private var loading = false

    //the minimum amount of items to have below your current scroll position before loading more
    private val visibleThreshold = 29

    private var firstVisibleItem: IntArray = IntArray(COLUMN_COUNT)
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var currentPage: Int = 1

    var nextPageAvailable = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView?.childCount ?: 0
        totalItemCount = layoutManager?.itemCount ?: 0

        firstVisibleItem[0] = layoutManager?.findLastVisibleItemPosition() ?: 0

        if (firstVisibleItem[0] != -1 && loading && totalItemCount > previousTotal) {
            val loadMoreType =
                    LOAD_MORE_TYPE

            // Check whether the last item is not the loading bar
            if (recyclerView?.adapter?.getItemViewType(previousTotal) != loadMoreType) {
                setLoadingStateComplete()
            }
        }

        if (!loading
                && (totalItemCount > visibleThreshold)
                && ((firstVisibleItem[0] + visibleItemCount + visibleThreshold) >= totalItemCount)
                && nextPageAvailable
        ) {
            currentPage++
            onLoadMore.invoke(currentPage)
        }
    }

    /**
     * IMPORTANT:
     * This method will be called automatically when the last item is not the progress bar (load more).
     * Called this method outside this class only if you're using StickyHeader on your RecyclerView.
     * And called it after resetState()
     */
    fun setLoadingStateComplete() {
        loading = false
        previousTotal = totalItemCount
    }

    fun resetState() {
        this.currentPage = 1
        this.previousTotal = 0
        this.loading = true
    }

    fun resetToPreviousState() {
        this.currentPage.dec()
        this.loading = false
    }
}

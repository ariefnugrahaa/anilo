package works.anilo.arief.presentation.main.repositories.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SearchHeaderModelItem(
        @SerializedName("total_count")
        val totalCount: String,
        @SerializedName("incomplete_results")
        val inCompleteResults: String,
        @SerializedName("items")
        val items: List<SearchModel>,
    ) {
        data class SearchModel(
                @SerializedName("id")
                val avatarUrl: String,
                @SerializedName("node_id")
                val eventsUrl: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("full_name")
                val fullName: String,
                @SerializedName("owner")
                val owner: SearchOwnerModel,
                @SerializedName("html_url")
                val htmlUrl: String,
                @SerializedName("description")
                val description: String,
                @SerializedName("fork")
                val fork: String,
                @SerializedName("url")
                val url: String,
        ) {
                data class SearchOwnerModel(
                        @SerializedName("avatar_url")
                        val avatarUrl: String,
                        @SerializedName("events_url")
                        val eventsUrl: String,
                        @SerializedName("followers_url")
                        val followersUrl: String,
                        @SerializedName("following_url")
                        val followingUrl: String,
                        @SerializedName("gists_url")
                        val gistsUrl: String,
                        @SerializedName("gravatar_id")
                        val gravatarId: String,
                        @SerializedName("html_url")
                        val htmlUrl: String,
                        @SerializedName("id")
                        val id: Int,
                        @SerializedName("login")
                        val login: String,
                        @SerializedName("node_id")
                        val nodeId: String,
                        @SerializedName("organizations_url")
                        val organizationsUrl: String,
                        @SerializedName("received_events_url")
                        val receivedEventsUrl: String,
                        @SerializedName("repos_url")
                        val reposUrl: String,
                        @SerializedName("site_admin")
                        val siteAdmin: Boolean,
                        @SerializedName("starred_url")
                        val starredUrl: String,
                        @SerializedName("subscriptions_url")
                        val subscriptionsUrl: String,
                        @SerializedName("type")
                        val type: String,
                        @SerializedName("url")
                        val url: String
                )
        }
}

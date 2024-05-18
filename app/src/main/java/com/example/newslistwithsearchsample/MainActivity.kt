package com.example.newslistwithsearchsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newslistwithsearchsample.presentation.NewsViewModel
import com.example.newslistwithsearchsample.presentation.ArticleListContent
import com.example.newslistwithsearchsample.presentation.detail.ArticleDetailScreen
import com.example.newslistwithsearchsample.ui.theme.NewsListWithSearchSampleTheme
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsListWithSearchSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CollapsingTopAppBar()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsingTopAppBar() {
    val listItems = buildList { repeat(100) { add(it) } }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var text by rememberSaveable { mutableStateOf("") }
    val viewModel = hiltViewModel<NewsViewModel>()
    val articles = viewModel.queryLatestNews().collectAsLazyPagingItems()

    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
//            val textState = remember { mutableStateOf(TextFieldValue()) }

            TopAppBar(
                title = {TextField(
                    value = text,
                    onValueChange = {
                        text = it
                        viewModel.notifyTextChanged(it)
                    },
                    label = { Text("Search news") }
                ) },
                scrollBehavior = scrollBehavior
            )
        },
    ) { paddingValues ->

        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = "article-list",
        ) {
            composable(
                "article-list",
                label = "Listing",
            ) {
                ArticleListContent(
                    articles,
                    navigateToDetail = { id ->
                        val url = URLEncoder.encode(id.urlToImage, StandardCharsets.UTF_8.toString())
                        val title = URLEncoder.encode(id.title, StandardCharsets.UTF_8.toString())
                        val description = URLEncoder.encode(id.description, StandardCharsets.UTF_8.toString())
                        navController.navigate("article/0/${url}/${title}/${description}")
                    }
                )
            }
            //createExercise/{exerciseId}/{workoutId}
            composable(
                "article/{id}/{url}/{title}/{description}",
                label = "Article",
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType },
                    navArgument("url") { type = NavType.StringType },
                    navArgument("title") { type = NavType.StringType },
                    navArgument("description") { type = NavType.StringType }

                ),
            ) {
                ArticleDetailScreen(snackbarHostState = snackbarHostState)
            }
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsListWithSearchSampleTheme {
        Greeting("Android")
    }
}

fun NavGraphBuilder.composable(
    route: String,
    label: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    addDestination(
        ComposeNavigator.Destination(
            provider[ComposeNavigator::class],
            content
        ).apply {
            this.route = route
            this.label = label // SET LABEL
            arguments.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )

}

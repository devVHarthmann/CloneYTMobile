package br.ulbra.ytclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.Cast
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val YtBg = Color(0xFF0F0F0F)
private val YtChip = Color(0xFF272727)
private val YtGray = Color(0xFFAAAAAA)
private val YtRed = Color(0xFFFF0000)

data class Video(
    val title: String,
    val channel: String,
    val meta: String,
    val duration: String,
    val c1: Color,
    val c2: Color
)

private val videos = listOf(
    Video("Como construir um app Android do zero em 30 minutos", "Dev na Prática", "312 mil visualizações • há 2 dias", "31:42", Color(0xFF283593), Color(0xFF5C6BC0)),
    Video("Lo-fi beats para estudar e relaxar 📚", "Chill Station", "1,2 mi de visualizações • há 1 mês", "3:12:08", Color(0xFF6A1B9A), Color(0xFFEC407A)),
    Video("Receita de pão de queijo mineiro perfeito", "Cozinha da Vovó", "89 mil visualizações • há 5 dias", "12:05", Color(0xFFEF6C00), Color(0xFFFFCA28)),
    Video("Top 10 gols mais bonitos da semana", "Futebol Total", "540 mil visualizações • há 12 horas", "8:47", Color(0xFF1B5E20), Color(0xFF66BB6A)),
    Video("Review completo: vale a pena em 2026?", "Tech Review BR", "204 mil visualizações • há 3 dias", "18:20", Color(0xFF004D40), Color(0xFF26A69A)),
    Video("Viajando pelo Sul do Brasil de moto", "Estrada Livre", "77 mil visualizações • há 1 semana", "24:33", Color(0xFF01579B), Color(0xFF4FC3F7)),
)

private val chips = listOf(
    "Todos", "Música", "Jogos", "Ao vivo", "Programação",
    "Futebol", "Culinária", "Podcasts", "Notícias", "Assistidos"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(background = YtBg, surface = YtBg)
            ) {
                YouTubeScreen()
            }
        }
    }
}

@Composable
fun YouTubeScreen() {
    Scaffold(
        containerColor = YtBg,
        topBar = { TopBar() },
        bottomBar = { BottomBar() }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            item { ChipsRow() }
            items(videos) { VideoCard(it) }
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(YtBg)
            .statusBarsPadding()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 30.dp, height = 21.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(YtRed),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Filled.PlayArrow, null, tint = Color.White, modifier = Modifier.size(16.dp))
        }
        Spacer(Modifier.width(4.dp))
        Text("YouTube", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold, letterSpacing = (-1).sp)
        Spacer(Modifier.weight(1f))
        Icon(Icons.Outlined.Cast, null, tint = Color.White, modifier = Modifier.padding(horizontal = 10.dp))
        Icon(Icons.Outlined.Notifications, null, tint = Color.White, modifier = Modifier.padding(horizontal = 10.dp))
        Icon(Icons.Outlined.Search, null, tint = Color.White, modifier = Modifier.padding(start = 10.dp))
    }
}

@Composable
fun ChipsRow() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(chips.size) { i ->
            val selected = i == 0
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (selected) Color.White else YtChip)
                    .padding(horizontal = 12.dp, vertical = 7.dp)
            ) {
                Text(
                    chips[i],
                    color = if (selected) Color.Black else Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun VideoCard(v: Video) {
    Column(Modifier.padding(bottom = 16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .background(Brush.linearGradient(listOf(v.c1, v.c2)))
        ) {
            Text(
                v.duration,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xCC000000))
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            )
        }
        Row(Modifier.padding(start = 12.dp, end = 4.dp, top = 12.dp)) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(v.c2),
                contentAlignment = Alignment.Center
            ) {
                Text(v.channel.first().toString(), color = Color.White, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    v.title,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(4.dp))
                Text("${v.channel} • ${v.meta}", color = YtGray, fontSize = 12.sp, maxLines = 2)
            }
            Icon(Icons.Outlined.MoreVert, null, tint = Color.White, modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun BottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(YtBg)
            .navigationBarsPadding()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomItem(Icons.Outlined.Home, "Início")
        BottomItem(Icons.Outlined.PlayCircle, "Shorts")
        Icon(
            Icons.Outlined.AddCircleOutline, null,
            tint = Color.White, modifier = Modifier.size(40.dp)
        )
        BottomItem(Icons.Outlined.Subscriptions, "Inscrições")
        BottomItem(Icons.Outlined.AccountCircle, "Você")
    }
}

@Composable
fun BottomItem(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, null, tint = Color.White, modifier = Modifier.size(26.dp))
        Text(label, color = Color.White, fontSize = 10.sp)
    }
}
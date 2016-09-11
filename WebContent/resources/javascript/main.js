
var songs = [];
function addToPlaylist(id){
	songs.push(id);
	console.log(songs);
	document.getElementById("songIdList").value = songs;
}



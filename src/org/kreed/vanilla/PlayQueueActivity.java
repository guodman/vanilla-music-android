package org.kreed.vanilla;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class PlayQueueActivity extends Activity {
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		ScrollView sv = new ScrollView(this);
		setContentView(sv);
		
		LinearLayout l = new LinearLayout(this);
		l.setOrientation(LinearLayout.VERTICAL);
		sv.addView(l);
		
		TextView title = new TextView(this);
		title.setText("Play Queue");
		l.addView(title);
		
		SongTimeline songs = PlaybackService.get(this).getSongList();
		for (Song s : songs.getAllSongs()) {
			TextView songView = new TextView(this);
			songView.setText(new StringBuilder(s.artist).
					append(" [").
					append(s.album).
					append(" - ").
					append(s.year).
					append("] ").
					append(s.title));
			l.addView(songView);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add(0, PlaybackActivity.MENU_PLAYBACK, 0, R.string.playback_view).setIcon(R.drawable.ic_menu_gallery).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		menu.add(0, PlaybackActivity.MENU_LIBRARY, 0, R.string.library).setIcon(R.drawable.ic_menu_music_library).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		menu.add(0, PlaybackActivity.MENU_PLAYQUEUE, 0, "Play Queue").setEnabled(false).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		menu.add(0, PlaybackActivity.MENU_PREFS, 0, R.string.settings).setIcon(R.drawable.ic_menu_preferences);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId()) {
		case PlaybackActivity.MENU_PLAYBACK:
			Intent intent = new Intent(this, FullPlaybackActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case PlaybackActivity.MENU_LIBRARY:
			startActivity(new Intent(this, LibraryActivity.class));
			return true;
		case PlaybackActivity.MENU_PREFS:
			startActivity(new Intent(this, PreferencesActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}

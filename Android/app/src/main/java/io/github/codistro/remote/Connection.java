package io.github.codistro.remote;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class Connection extends AsyncTask<String, Void, Void> {
    private Exception exception;

    protected Void doInBackground(String... commands) {
        try {
            try {
                Client client = new Client(commands[0]);
                client.send(commands[1]);
            }
            catch (Exception e){
                Log.d("TAG", e.toString());
            }

        } catch (Exception e) {
            this.exception = e;
        } finally {
        }
        return null;
    }

    protected void onPostExecute() {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}

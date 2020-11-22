package at.fh.swengb.loggingviewsandactivity

import android.os.AsyncTask
import android.util.Log

class SleepyAsyncTask(): AsyncTask<Unit, Unit, Unit>() {

    override fun onPreExecute() {
        super.onPreExecute()
        Log.e("SleepingAsyncTask", "Going to sleep")
        Thread.currentThread().name
    }

    override fun doInBackground(vararg params: Unit?) {
        Thread.sleep(5000)
        Thread.currentThread().name
    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)
        Log.e("SleepingAsyncTask", "Done sleeping")
        Thread.currentThread().name
    }

}
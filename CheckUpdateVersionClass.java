public void CheckUpdateVersion() {
	String currentVersion = ""
    try {
        currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName; // getting our app's package name 
    } catch (PackageManager.NameNotFoundException e) {
        e.printStackTrace();
    }
    String finalCurrentVersion = currentVersion;
    new CheckIsUpdateReady("https://play.google.com/store/apps/details?id=" + getPackageName() + "&hl=en", new UrlResponce() {
        @Override
        public void onReceived(String responseStr) {
            if (finalCurrentVersion != ""){
                if (!responseStr.equals(finalCurrentVersion)){
                    Toast.makeText(getBaseContext(),responseStr + " version has been released,please update your app",Toast.LENGTH_SHORT).show();
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
                    int pid=android.os.Process.myPid();
                    android.os.Process.killProcess(pid);
                }catch(android.content.ActivityNotFoundException anfe){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
                    int pid=android.os.Process.myPid();
                    android.os.Process.killProcess(pid);
                }
                }
            }
        }
    }).execute();
}

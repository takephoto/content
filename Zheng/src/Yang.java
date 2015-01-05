import java.text.DateFormatSymbols;
import java.util.Calendar;


public class Yang {
	public static void main(String[] args) {
		/*String toast = "已将闹钟设置为从现在起1小时1分钟后提醒。" ;
		int start = toast.indexOf("起") ;
		
		String toast1 = toast.substring(start+1) ;
		
		
		toast1 = toast1.substring(0, toast1.length() - 4) ;*/
		DateFormatSymbols dfs = new DateFormatSymbols();
        String[] mShortWeekDayStrings = dfs.getShortWeekdays();
       String[] mLongWeekDayStrings = dfs.getWeekdays();
       
       for(String ms : mLongWeekDayStrings)
		System.out.println(ms) ;
       
       
       
       
 	}
}

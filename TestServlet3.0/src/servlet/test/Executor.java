package servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;

public class Executor implements Runnable{
	private AsyncContext tcx=null;
	public Executor(AsyncContext tcx){
		this.tcx=tcx;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
			int num = 0;
		   int max = 100;
		   int interval = 1000;
			try {
				//tcx.setTimeout((max + 1) * interval);
				Thread.sleep(10000);
				PrintWriter out=tcx.getResponse().getWriter();
				out.print("业务处理完成时间"+new Date());
				out.flush();
				
				tcx.complete();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
}

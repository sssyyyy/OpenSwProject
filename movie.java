package exercise;

import java.util.Scanner;

import org.json.simple.parser.ParseException;

public class movie {
	public static void main(String[] args) throws ParseException, InterruptedException {
		Scanner scn=new Scanner(System.in);
		boolean keep=true;
		while(keep)
		{
			System.out.println("\t##########__Opensw_Cinema__##########");
			System.out.println("1)영화 예매 \t2)예매 확인 \t3)예매 취소 \t4)종료");
			System.out.print("번호를 입력하세요: ");
			int num =scn.nextInt();
			switch(num)
			{
			case 1:
				mmovie.reserve();
				break;
			case 2:
				mmovie.check();
				break;
			case 3:
				mmovie.cancle();
				break;
			case 4:
				mmovie.exit();
				keep=false;
				break;
			default:
				System.out.println("다시 입력하세요:");
				break;
			}
		}
	}
}

class Clear
{
	public static void Screen() 
 	{
	    for (int i = 0; i < 80; i++)
	      System.out.println("");
	}
}

class show
{
	public static void seat(int [][] movie)
	{
		System.out.println("   <Screen>");
		System.out.println("  1 2 3 4 5 6");
		for(int i=0;i<6;i++)
		{
			System.out.print((char)((int)('A'+i))+" ");
			for(int j=0;j<6;j++)
			{
				if(movie[i][j]==0)
				{
					System.out.print("○ ");
				}
				else if(movie[i][j]<0)
				{
					System.out.print("● ");
				}
				else
				{
					System.out.print("♥ ");
				}
			}
			System.out.print("\n");
		}
		System.out.print("\n●는 빈공간(예매불가),○는 예매 가능,♥는 예매된 좌석 ");
	}
}

class choose{
	public static void seat(int [][] movie) throws InterruptedException
	{
		boolean b=true;
		while(b)
		{
			Scanner sn = new Scanner(System.in);
			System.out.print("\n 좌석을 선택해주세요(ex.A 1) :");
			String str = sn.nextLine();
			char alpa=str.charAt(0);
			char cnum=str.charAt(2);
			int num=(int)cnum-(int)('1');
			int ialpa = (int)alpa-(int)('A');
			
			if(movie[ialpa][num]==0)
			{
			 movie[ialpa][num]=(int)(Math.random()*10000)+1;
			 Clear.Screen();
			 show.seat(movie);
			 System.out.println("\n좌석 번호: "+alpa+" "+cnum);
			 System.out.println("예매번호: "+movie[ialpa][num]+ "\n위 좌석으로 예매가 완료되었습니다!\n");
			 b=false;
			 Thread.sleep(6000);
			 Clear.Screen();
			}
			else if (movie[ialpa][num]>0)
			{
				System.out.print(" 이미 예약된 좌석입니다.\n 다른 좌석을 선택해주세요.\n");
			}
			else
			{
				System.out.print(" 사회적 거리두기로 인하여 이 좌석은 예매가 불가합니다.\n 다른 좌석을 선택해주세요.\n");
			}
		}
	}
}
class mmovie
{
	static int [][] Para=new int[][] {{-1,0,-1,0,-1,0},{0,-1,0,-1,0,-1},{-1,0,-1,0,-1,0},{0,-1,0,-1,0,-1},{-1,0,-1,0,-1,0},{0,-1,0,-1,0,-1}};
	static int [][] Wail=new int[][] {{-1,0,-1,0,-1,0},{0,-1,0,-1,0,-1},{-1,0,-1,0,-1,0},{0,-1,0,-1,0,-1},{-1,0,-1,0,-1,0},{0,-1,0,-1,0,-1}};
	static int [][] About=new int[][]{{-1,0,-1,0,-1,0},{0,-1,0,-1,0,-1},{-1,0,-1,0,-1,0},{0,-1,0,-1,0,-1},{-1,0,-1,0,-1,0},{0,-1,0,-1,0,-1}};
	
	public static void reserve() throws ParseException, InterruptedException
	{
		Clear.Screen();
		Scanner scn = new Scanner(System.in);
		ApiExamSearchMovie api1=new ApiExamSearchMovie("기생충");
		ApiExamSearchMovie api2=new ApiExamSearchMovie("곡성");
		ApiExamSearchMovie api3=new ApiExamSearchMovie("어바웃 타임");
		System.out.println("★★★★★★★★★★★★  Now Showing ★★★★★★★★★★★★ \n");
		System.out.println("================ Movie #1 ==================");
		api1.ShowMovie();
		System.out.println("================ Movie #2 ==================");
		api2.ShowMovie();
		System.out.println("================ Movie #3 ==================");
		api3.ShowMovie();
		System.out.print("어떤 영화를 관람하시겠습니까?(1 or 2 or 3)");
		int in=scn.nextInt();
		switch(in)
		{
		case 1:
			Clear.Screen();
			System.out.println("영화 기생충을 선택하셨습니다.\n");
			show.seat(Para);
			choose.seat(Para);
			
			break;
		case 2:
			Clear.Screen();
			System.out.println("영화 곡성을 선택하셨습니다.");
			show.seat(Wail);
			choose.seat(Wail);
			
			break;
		case 3:
			Clear.Screen();
			System.out.println("영화 어바웃 타임을 선택하셨습니다.");
			show.seat(About);
			choose.seat(About);
		
			break;
		default:
			System.out.println("다시 입력하세요:");
			break;
		}
	}
	static void check() throws InterruptedException
	{
		System.out.println("================ 예매 확인 ==================");
		System.out.print("예매번호: ");
		Scanner sca=new Scanner(System.in);
		int revnum=sca.nextInt();
		boolean n =false;
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<6;j++)
			{
				if(revnum==0)
				{
					continue;
				}
				if(Para[i][j]==revnum)
				{
					System.out.println("고객님의 예매 정보입니다.");
					System.out.println("영화: 기생충");
					System.out.println("좌석: "+(char)((int)('A')+i)+" "+(j+1));
					System.out.println("예매번호: "+revnum);
					n=true;
					Thread.sleep(6000);
					Clear.Screen();
				}
				if(Wail[i][j]==revnum)
				{
					System.out.println("고객님의 예매 정보입니다.");
					System.out.println("영화: 곡성");
					System.out.println("좌석: "+(char)((int)('A')+i)+" "+(j+1));
					System.out.println("예매번호: "+revnum);
					n=true;
					Thread.sleep(6000);
					Clear.Screen();
				}
				if(About[i][j]==revnum)
				{
					System.out.println("고객님의 예매 정보입니다.");
					System.out.println("영화: 어바웃 타임");
					System.out.println("좌석: "+(char)((int)('A')+i)+" "+(j+1));
					System.out.println("예매번호: "+revnum);
					n=true;
					Thread.sleep(6000);
					Clear.Screen();
				}
				if(i==5&&j==5&&!n)
				{
					System.out.println("예매번호가 존재하지 않습니다.\n메뉴 화면으로 돌아갑니다.");
					Thread.sleep(1500);
					Clear.Screen();
				}
			}
		}
	}
	
	static void cancle() throws InterruptedException
	{
		System.out.println("================ 예매 취소 ==================");
		Scanner sc=new Scanner(System.in);
		System.out.print("예매번호: ");
		int rnum=sc.nextInt();
		boolean n =false;
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<6;j++)
			{
				if(rnum==0)
				{
					continue;
				}	
				if(Para[i][j]==rnum)
				{
					System.out.println("영화: 기생충");
					System.out.println("좌석: "+(char)((int)('A')+i)+" "+(j+1));
					System.out.println("예매번호: "+rnum);
					Para[i][j]=0;
					n=true;
					System.out.println("위 영화예매가 취소 되었습니다.");
					Thread.sleep(6000);
					Clear.Screen();
				}
				if(Wail[i][j]==rnum)
				{
					System.out.println("영화: 곡성");
					System.out.println("좌석: "+(char)((int)('A')+i)+" "+(j+1));
					System.out.println("예매번호: "+rnum);
					Wail[i][j]=0;
					n=true;
					System.out.println("위 영화예매가 취소 되었습니다.");
					Thread.sleep(6000);
					Clear.Screen();
				}
				if(About[i][j]==rnum)
				{
					System.out.println("영화: 어바웃 타임");
				    System.out.println("좌석: "+(char)((int)('A')+i)+" "+(j+1));
					System.out.println("예매번호: "+rnum);
					About[i][j]=0;
					n=true;
					System.out.println("위 영화예매가 취소 되었습니다.");
					Thread.sleep(6000);
					Clear.Screen();
				}
				if(i==5&&j==5&&!n)
				{
					System.out.println("예매번호가 존재하지 않습니다.\n메뉴 화면으로 돌아갑니다.");
					Thread.sleep(1500);
					Clear.Screen();
				}
			}
		}
	}
	static void exit()
	{
		System.exit(0);
	}
}
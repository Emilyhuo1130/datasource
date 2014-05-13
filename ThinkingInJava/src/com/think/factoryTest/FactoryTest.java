package com.think.factoryTest;

public class FactoryTest {

	/**
	 * @param args
	 */
	private static int j;
	private static int i;
	private int m;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Factiry factory=new FactoryImpl();
		Service service = factory.getService();
		User user = factory.getUser();
		FactoryTest.A.privateout();
	}
	public static class A{
		private static void privateout(){
			System.out.println("00000000000");
			System.out.println(j);
			
			class C{
				public void get(){
					
				}
			}
			
		}
		public static void publicout(){
			System.out.println("00000000000");
		}
		public  void publicout2(){
			System.out.println("00000000000");
			System.out.println(i);
			
		}
		
	}
	public class B{
		public void out(){
			System.out.println(j);
			System.out.println(m);
			j--;
			class C{
				public void get(){
					
				}
			}
			new C().get();
		}
		public B get(){
			return B.this;
			
		}
	}
	

	public static A get(){
		return new A();
	}

}

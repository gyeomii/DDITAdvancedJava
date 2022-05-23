package JavaEnum.PlanetArea;

public class PlanetArea {
	 public enum Planet {
	        수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
	 
	        private int str;
	 
	        Planet(int data) {
	            str = data;
	        }
	 
	        public int getStr() {
	            return str;
	        }
	    }
	 
	    public static void main(String[] args) {
	 
	        for (Planet pl : Planet.values()) {            
	            System.out.println(pl + "의 면적 : " + (double)(4*3.141592*Math.pow(pl.getStr(), 2)) + " kM^2");
	        }
	        
	    }
}

package JavaEnum.PlanetArea;

public class PlanetArea {
	public enum Planet {
		//행성명(반지름(km))
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
		
		private double radius;

		Planet(double data) {
			this.radius = data;
		}

		public double getRadius() {
			return radius;
		}
	}

	public static void main(String[] args) {

		for (Planet pl : Planet.values()) { // 면적 : 4πr^2, 부피 : (4/3) * (πr^3)
			System.out.printf("%s의 면적 : %f㎢, 부피 : %f㎦\n", pl.name(), (4 * Math.PI * Math.pow(pl.getRadius(), 2)), (4 * Math.PI * Math.pow(pl.getRadius(), 3)) / 3);
		}

	}
}

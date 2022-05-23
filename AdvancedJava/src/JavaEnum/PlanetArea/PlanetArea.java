package JavaEnum.PlanetArea;

public class PlanetArea {
	public enum Planet {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);

		private double str;

		Planet(double data) {
			this.str = data;
		}

		public double getStr() {
			return str;
		}
	}

	public static void main(String[] args) {

		for (Planet pl : Planet.values()) {
			System.out.println(pl + "의 면적 : " + (4 * Math.PI * Math.pow(pl.getStr(), 2)) + "㎢");
		}

	}
}

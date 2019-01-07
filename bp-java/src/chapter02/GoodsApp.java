package chapter02;

public class GoodsApp {

	public static void main(String[] args) {
		Goods goods = new Goods();
		goods.setName("nikon");
		goods.setPrice(44000);
		// information hiding
		goods.setPrice(-1);
		goods.setCountSold(100);
		goods.setCountStock(50);
		
		System.out.println(
				goods.getName() + ":" +
				goods.getPrice() + ":" +
				goods.getCountStock() + ":" +
				goods.getCountSold()
		);
		
		goods.showInfo();
		System.out.println("calcDiscountPrice(): " + goods.calcDiscountPrice(0.3f));
		
		Goods goods2 = new Goods();
		Goods goods3 = new Goods();
		
		System.out.println("countOfGoods: " + Goods.countOfGoods);
	}
	
}

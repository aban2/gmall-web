package gmall.model;

/**
 * 和Goods表做一个一一映射
 * <p>
 * Created by elephant on 16/7/8.
 */
public class GoodsDetail {
	private int itemId;
	private String itemName;
	private float price;
	private String brandName;
	private float star;
	private String rdate;
	private String comment;

	public int getGoodsId() {
		return itemId;
	}

	public void setGoodsId(int itemId) {
		this.itemId = itemId;
	}

	public String getGoodsName() {
		return itemName;
	}

	public void setGoodsName(String itemName) {
		this.itemName = itemName;
	}

	public float getGoodsPrice() {
		return price;
	}

	public void setGoodsPrice(float price) {
		this.price = price;
	}

        public String getGoodsBrandName() {
		return brandName;
	}

	public void setGoodsBrandId(String brandName) {
		this.brandName = brandName;
	}
        
        public float getGoodsStar() {
		return star;
	}

	public void setGoodsStar(float star) {
		this.star = star;
        }
        
        public  String getGoodsRdate() {
		return rdate;
	}

	public void setGoodsRdate(String rdate) {
		this.rdate = rdate;
	}
        
        public String getGoodsComment() {
		return comment;
	}

	public void setGoodsComment(String comment) {
		this.comment = comment;
	}
}

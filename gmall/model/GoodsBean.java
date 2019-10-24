package gmall.model;

/**
 * 和Goods表做一个一一映射
 * <p>
 * Created by elephant on 16/7/8.
 */
public class GoodsBean {
	private int id;
	private String name;
	private float price;
	private int brandId;
	private float star;
	private int usercount;
	private float score;

	public int getGoodsId() {
		return id;
	}

	public void setGoodsId(int id) {
		this.id = id;
	}

	public String getGoodsName() {
		return name;
	}

	public void setGoodsName(String name) {
		this.name = name;
	}

	public float getGoodsPrice() {
		return price;
	}

	public void setGoodsPrice(float price) {
		this.price = price;
	}

        public int getGoodsBrandId() {
		return brandId;
	}

	public void setGoodsBrandId(int brandId) {
		this.brandId = brandId;
	}
        
        public float getGoodsStar() {
		return star;
	}

	public void setGoodsStar(float star) {
		this.star = star;
        }
        
        public int getGoodsUsercount() {
		return id;
	}

	public void setGoodsUsercount(int usercount) {
		this.usercount = usercount;
	}
        
        public float getGoodsScore() {
		return price;
	}

	public void setGoodsScore(float score) {
		this.score = score;
	}
}

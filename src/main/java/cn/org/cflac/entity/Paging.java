package cn.org.cflac.entity;

import java.util.List;

public class Paging<T> {

	private Integer draw;
    private Integer page;// 当前的页数
    private Integer length;// 每页显示数量，limit函数第二个参数
    private Integer recordsTotal;// 总记录数
    private Integer pages ;// 总页数
    private Integer start;// 开始位置，limit函数第一个参数
    private Integer start1;
    private Integer end;
    private List<T> data;
    private  Integer recordsFiltered;
    
    public Paging() {
    }


    public Paging(Integer pagNum, Integer length, Integer recordsTotal,Integer draw) {
        this.page = pagNum;
        this.length = length;
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        // 计算总页数
        if (recordsTotal % length == 0) {
            this.pages  = recordsTotal / length;
        } else {
            this.pages  = (recordsTotal / length) + 1;
        }
        // 确定limit函数的第一个参数的值
        this.start = pagNum + length;
        this.start1 = 1;
        this.end = 5;
        if (pages  <= 5) {
            this.end = this.pages ;
        } else {// pagNum=6;start=4;end=8
            this.start1 = pagNum - 2;
            this.end = pagNum + 2;
        }
        if (start1 < 0) {
            this.start1 = 1;
            this.end = 5;
        }
        // 一共有10页，现在就在第10页，那么end=12，错误；则end=this.pages ;且下面导航栏还是要出现5个，则start=end-5
        if (end > this.pages ) {
            this.end = pages ;
            this.start1 = end - 5;
        }
    }
    
    
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getStart1() {
		return start1;
	}
	public void setStart1(Integer start1) {
		this.start1 = start1;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
    
    
}

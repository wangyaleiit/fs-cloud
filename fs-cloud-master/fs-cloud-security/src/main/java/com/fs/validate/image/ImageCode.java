package com.fs.validate.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import com.fs.validate.ValidateCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * 
 * @ClassName ImageCode
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月1日
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ImageCode extends ValidateCode {
	
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image; 
	
	public ImageCode(BufferedImage image, String code, int expireIn){
		super(code, expireIn);
		this.image = image;
	}
	
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}
}

/**
 * 作者：周懿
 * 时间：2023.8.23
 * 功能：货物信息的实体类
 */
package bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class goodInfo {

    private Integer id;

    private String goodName;

    private String manufacturer;

    private Double buyPrice;

    private Double sellPrice;

    private Integer goodNum;
}

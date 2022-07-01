package TDMs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomTDM {
    private String StudentId;
    private String sName;
    private double dueValue;
    private String roomType;
    private String regId;
}

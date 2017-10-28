package Mrma;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@RequiredArgsConstructor
@ToString()
public class Say {

    private String name;
    @NonNull
    private String sex;


}

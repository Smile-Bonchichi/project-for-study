package kg.it.academy.OnlineAuction.dto.Item.response;

import kg.it.academy.OnlineAuction.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemResponseDto {
    String name;

    String description;

    List<Category> category;
}

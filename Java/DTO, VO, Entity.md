### DTO

Data Transfer Object

- 레이어간 데이터를 전송하기 위해 정의된 객체
- 주로 json serialization과 같은 **직렬화**에 사용되는 객체
- DTO는 원래 DAO( data access object )패턴에서 유래된 단어로 
DAO에서 DB처리 로직을 숨기고 DTO라는 결과값을 내보내는 용도로 활용했었다.
- 데이터 교환만을 위해 사용하므로 로직을 갖지 않고, getter/setter 메소드만 갖는다.
- 단지 데이터를 전송하기 위한 역할로 setter 또한 초기화시에 사용되는 역할을 위해 선언할 뿐이다. 최근에는 static of() 메소드 같은 팩토리 메소드 패턴으로 setter를 대체해서 초기화하기도 함

```java
package com.de.project101.dto;

import com.de.project101.model.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
	private String id;
	private String title;
	private boolean done;
	
	public TodoDTO(final TodoEntity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.done = entity.isDone();
		
	}
	
	public static TodoEntity toEntity(final TodoDTO dto) {
		return TodoEntity.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.done(dto.isDone())
				.build();
	}

}
```

### ****VO****

Value Object

- 값 그 자체를 표현하는 객체
- 내용물이 값 자체를 의미하기 때문에 **read only**
- 로직을 포함할 수 있으며, 객체의 불변성(객체의 정보가 변경하지 않음)을 보장한다.
- 값 자체를 의미하기 때문에 같은 객체라는 것을 보장하기 위해서는 Object 클래스의 `equals()`와 `hashCode()`를 오버라이딩해야 한다.
- 서로 다른 이름을 갖는 VO 인스턴스더라도 **모든 속성 값이 같다면!** 두 인스턴스는 같은 객체라고 할 수 있다.

### Entity

- 실제 DB의 테이블과 매핑되는 객체이다. `id`를 통해 각각의 Entity를 구분한다.
- VO와 마찬가지로 로직을 가질 수 있다.
- entity 객체는 DB Layer와 많이 밀접한 객체라서 **Setter 접근을 금지**하고 **기본생성자 사용을 제한(protected)**해 일관성을 유지해야한다.
- entity의 값이 변경될 여지가 발생하여 영속성 영역에서 얻어진 신뢰성있는 값들이 훼손될 여지가 존재한다.

```java
package com.de.project101.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Todo")
public class TodoEntity {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;		// 이 오브젝트의 id
	private String userId;	// 이 오브젝트를 생성한 사용자의 id
	private String title; 	// Todo 타이틀
	private boolean done;	// true : todo를 완료한 상태(checked)
}
```

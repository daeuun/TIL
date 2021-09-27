## 트랜잭션

`데이터베이스의 상태를 변경하는 작업` 또는 `한번에 수행되어야 하는 연산들`

1) 데이터베이스의 상태를 변환시키는 하나의 논리적 기능을 수행하기 위한 작업단위

2) 트랜잭션 내에 묶인 하나의 작업단위는 반드시 완전히 수행되어야 한다.  
만약 모두 수행되지 않고, 중간에 하나라도 실패한다면 전체 명령문은 취소되어야 한다.  
중간에 예외가 발생해서 작업을 완료할 수 없다면 아예 작업이 시작되지 않은 것처럼 초기 상태로 돌려놔야 하는데 이것이 트랜잭션이다.

<br>

## 스프링의 트랜잭션 지원

스프링은 코드 기반의 트랜잭션 처리(Programmatic Transaction) 뿐만 아니라 `선언적 트랜잭션`(Declarative Transaction)을 지원하고 있다.

<br>

## 선언적 트랜잭션 처리

- 선언적 트랜잭션은 설정 파일이나 어노테이션을 이용해서 트랜잭션의 범위, 롤백 규칙 등을 정의
- 다음과 같은 2가지 방식으로 정의
1.  `<tx:advice>` 태그를 이용한 트랜잭션 처리
2.  `@Transactional` 어노테이션을 이용한 트랜잭션 설정

```java
/**     
* 사업부 정보 등록,수정     
* @param map     
* @return     
* @throws Exception     
*/    

@Transactional    
public int saveDivisionData(Map<String, Object> map) throws Exception {      
	
	int cnt = companyDAO.saveDivisionData(map);
      
	// 사업부 수정이 정상적으로 처리되면 대상 사업부를 확산대상으로 지정한다.      
	if(cnt == 1){        
		companyDAO.saveExtensionTarget(map);      
	}
        
	return cnt;    
}
```
<br>

## **@Transactional을 써주는 이유?**

companyDAO.saveDivisionData에서 처리한 쿼리문이 정상적으로 완료가 되고

companyDAO.saveextensionTarget에서 처리 도중 에러가 났을 때,

companyDAO.saveDivisionData에서 `처리한 쿼리를 자동 rollback` 해주기 위해 사용된다.

만약 어노테이션을 써주지 않는다면, 정상적으로 완료가 되었기 때문에 직접 save한 division 데이터를 복구 시켜놔야함

<br>

## @Transactional 옵션

**1. isolation**

트랜잭션에서 일관성없는 데이터 허용 수준을 설정한다

**2. propagation**

트랜잭션 동작 도중 다른 트랜잭션을 호출할 때, 어떻게 할 것인지 지정하는 옵션이다

**3. noRollbackFor**

특정 예외 발생 시 rollback하지 않는다.

**4. rollbackFor**

특정 예외 발생 시 rollback한다.

**5. timeout**

지정한 시간 내에 메소드 수행이 완료되지 않으면 rollback 한다. (-1일 경우 timeout을 사용하지 않는다)

**6. readOnly**

트랜잭션을 읽기 전용으로 설정한다.

<br>

## @Transactional(rollbackFor = Exception.class)

@Transactional 은 기본적으로 Unchecked Exception, Error 만을 rollback하고 있다.

Checked Exception은 예상된 에러이고 Unchecked Exception, Error는 예상치 못한 에러이기 때문에

모든 예외에 대해서 rollback을 진행하고 싶을 경우(rollbackFor = Exception.class) 를 붙여야 한다. 

<br>

## @Transaction 특징

- 인터페이스를 구현한 클래스로 선언된 빈은 인터페이스 메소드에 한해서 트랜잭션이 적용됨
- 인터페이스에 붙은 @Transactional 선언은 인터페이스 내의 모든 메소드에 적용됨
- 동시에 메소드 레벨에도 @Transactional을 지정할 수 있다. 메소드 선언 > 인터페이스 선언
- 클래스의 @Transactional > 인터페이스의 @Transactional
- @Transactional 적용 대상은 미리 결정하고 애플리케이션 안에서 통일하는게 좋음. 인터페이스와 클래스 양쪽에 불규칙하게 @Transactional이 혼용되는 건 바람직하지 못함

<br>

## 트랜잭션 연산
**롤백(Rollback)**

- 트랜잭션 작업 도중 오류가 발생하면 트랜잭션 처리를 시작하기 이전의 상태로 되돌린다

**커밋(Commit)**

- 트랜잭션 작업을 성공적으로 마치고 데이터 베이스에 영구적으로 반영한다
[Testing 블로그 포스팅 주소](https://velog.io/@wish17/%EC%BD%94%EB%93%9C%EC%8A%A4%ED%85%8C%EC%9D%B4%EC%B8%A0-%EB%B0%B1%EC%97%94%EB%93%9C-%EB%B6%80%ED%8A%B8%EC%BA%A0%ED%94%84-54%EC%9D%BC%EC%B0%A8-Spring-MVC-%ED%85%8C%EC%8A%A4%ED%8C%85Testing)

# [Spring MVC] 테스팅(Testing)

## 단위 테스트(Unit Test)

단위 테스트 코드는 대부분 메서드 단위로 작성된다.

> 테스트 케이스(Test Case)
- 테스트를 위한 입력 데이터, 실행 조건, 기대 결과를 표현하기 위한 명세
- 메서드 등 하나의 단위를 테스트하기 위해 작성하는 테스트 코드

### F.I.R.S.T 원칙

- Fast (빠르게): 테스트 케이스는 빠르게 실행되어야 한다. 느린 테스트는 개발자들이 자주 실행하지 않게 되어 버그를 찾기 어렵다.
- Independent (독립적으로): 각각의 테스트는 서로 독립적으로 실행되어야 한다. 다른 테스트에 의존하는 경우, 실패한 테스트를 찾기 어렵게 만든다.
Repeatable (반복 가능하게): 테스트 케이스는 반복 가능해야 한다. 언제든지 실행 가능하고, 결과가 일관되게 나와야 한다.
- Self-Validating (자가 검증 가능하게): 단위 테스트는 스스로 검증이 가능해야 한다. 테스트가 성공한 것과 실패한 것을 명확하게 알려줘야 한다.
- Timely (적시에): 단위테스트는 기능 구현을 하기 전에 작성되어야 한다. 
(코딩테스트처럼 결과를 정해두고 거기에 맞춘 기능을 짠다고 생각하면 됨)
이를 통해 개발자는 코드 수정 후 변경 사항에 대한 영향을 빠르게 확인할 수 있다.


>Assertion(어써션)
- 예상하는 결과 값이 참(true)이길 바라는 것
- 테스트 결과를 검증할 때 주로 사용하는 단어

#### JUnit 없이 비즈니스 로직에 단위 테스트 적용

[풀코드 GitHub주소](https://github.com/wish9/Practice-Testing/commit/72d74ba91e5ed6e86a185fd68cee205076977696)

기존에 코딩테스트 하던 것 처럼 테스트하는 방식이다.

***

# JUnit을 사용한 단위 테스트

> JUnit
- Java의 테스트 프레임워크 중 하나
- Spring Boot의 디폴트 테스트 프레임워크

[Assertion 메서드 연습 GitHub 주소](https://github.com/wish9/Practice-Testing/commit/9e7a9f1e41ec1fd2d53f7c8ed355e4a6952259c9)

``@DisplayName("테스트 이름")``
- 테스트 이름을 정하는 애너테이션

``assertEquals(aaa, bbb)``
- JUnit에서 사용하는 두 값이 같은지 비교하는 메서드
- 첫번째 파라미터와 두번째 파라미터가 같은지 비교

``assertNotNull()``
- 테스트 대상 객체가 null 이 아닌지를 테스트하는 메서드
-  첫 번째 파라미터는 테스트 대상 객체이고, 두 번째 파라미터는 테스트에 실패했을 때, 표시할 메시지

``assertThrows()``
- 동작 과정 중에 예외가 발생하는지 테스트하는 메서드
- 첫 번째 파라미터에는 발생이 기대되는 예외 클래스를 입력, 두 번째 파라미터인 람다 표현식에는 테스트 대상 메서드를 호출

```java
//예시
assertThrows(NullPointerException.class, () -> getCryptoCurrency("XRP"));
```
 예외 클래스의 상속 관계를 이해한 상태에서 테스트 실행 결과를 예상해야 된다.
(``NullPointerException`` 은 ``RuntimeException`` 을 상속하는 하위 타입이고, ``RuntimeException`` 은 ``Exception`` 을 상속하는 하위 타입이다.)
 
> ``Executable`` 함수형 인터페이스
- assertThrows() 의 두 번째 파라미터인 **람다 표현식**은 JUnit에서 지원하는 Executable 함수형 인터페이스다.
- Java에서 지원하는 함수형 인터페이스 중에서 리턴값이 없는 ``Consumer``에 해당 (리턴값이 없음)

***

#### 자바 내장 함수형 인터페이스 종류

``Consumer 함수형 인터페이스``
- 인수를 받아들이고, 리턴 값을 반환하지 않는다.

``Supplier 함수형 인터페이스``
- 인수를 받지 않고, 리턴 값을 반환한다.

``Function 함수형 인터페이스``
- 하나의 인수를 받아들이고, 결과를 반환한다.

``Predicate 함수형 인터페이스``
- 인수를 받아들이고, true 또는 false 값을 반환한다.

``Executable 함수형 인터페이스``
- 이러한 함수형 인터페이스와 함께 사용하여 자바의 함수형 프로그래밍을 구현할 수 있다.

***

### 테스트 케이스 실행 전, 전처리

[연습코드 GitHub주소](https://github.com/wish9/Practice-Testing/commit/441a401e8ed0a6aa97d788e4dd984c8a1ef83c27)

``@BeforeEach``
- 테스트 케이스를 실행하기 전 전처리 과정에 사용하는 애너테이션
- 각각의 테스트 케이스가 실행될 때 마다 실행되도록 함

``@BeforeAll``
- 테스트 케이스를 실행하기 전 전처리 과정에 사용하는 애너테이션
- 클레스 레벨에서 테스트 케이스를 한꺼번에 실행 시키면 테스트 케이스가 실행되기 전에 딱 한번만 실행 됨
- 이 애너테이션을 추가한 메서드는 정적 메서드(static method)여야 한다.

``assertDoesNotThrow()``
- 예외가 발생하지 않는다고 기대하는 Assertion 메서드

```java
 assertDoesNotThrow(() -> getCryptoCurrency("XRP"));
 ```

***

### 테스트 케이스 실행 후, 후처리

``@AfterEach``
- ``@BeforeEach``와 동작 방식 같음
- 호출되는 시점만 반대

``@AfterAll``
- ``@BeforeAll``과 동작 방식 같음
- 호출되는 시점만 반대

***

### Assumption을 이용한 조건부 테스트

Assumption 기능 = 특정 환경에만 테스트 케이스가 실행 되도록 하는 기능

[연습코드 GitHub주소](https://github.com/wish9/Practice-Testing/commit/4eed3043d26c97bd2c6f854cacb600a48dcfb970)

``assumeTrue()``
- 매개변수로 전달된 조건이 true 일 때에만 테스트를 실행
- 테스트가 실행되지 않은 경우, 해당 테스트는 통과한 것으로 처리된다.
- 테스트의 **전제 조건**을 검증하는데 사용

#### 매개변수로 false가 들어갈 경우
![](https://velog.velcdn.com/images/wish17/post/fb1221fc-da9d-4643-9bd9-84faf9b79b42/image.png)


``assertTrue()``

- 매개변수로 전달된 조건이 true 인지를 검증한다.
- 테스트 결과가 true 가 아닌 경우, 해당 테스트를 실패로 처리한다.
- **결과값**을 검증하는데 사용

#### 매개변수로 false가 들어갈 경우
![](https://velog.velcdn.com/images/wish17/post/22d698dc-9ebd-4441-89f9-2f95188a71f9/image.png)


위 두가지 메서드 모두 조건 검증에 사용되는 메서드이지만, assumeTrue 메서드는 검증이 실패해도 해당 테스트를 통과한 것으로 처리하고, 해당 테스트를 실행하지 않는다. 반면 assertTrue 메서드는 검증이 실패하면 해당 테스트를 실패로 처리한다. 따라서, assumeTrue 메서드는 전제 조건을 검증할 때 사용하고, assertTrue 메서드는 결과 값을 검증할 때 사용한다.

***

# Hamcrest를 사용한 Assertion

> Hamcrest
- JUnit 기반의 단위 테스트에서 사용할 수 있는 Assertion Framework
- Assertion을 위한 매쳐(Matcher)가 자연스러운 문장으로 이어지므로 가독성이 향상 된다.
- 테스트 실패 메시지를 이해하기 쉽다.
- 다양한 Matcher를 제공한다.

[연습내용 GitHub 주소](https://github.com/wish9/Practice-Testing/commit/a0c894bec98d81794b402fc5813b572c3ea25bb2)

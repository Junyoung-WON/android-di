# 기능 요구 사항

## 0.5단계

### 기능 요구 사항

**생성자 주입 - 수동**  
다음 문제점을 해결한다.  

- [x] 테스트하기 어렵다.
  - 테스트가 쉬워지도록 개선한다.
    - Repository 인터페이스를 정의하고, ViewModel의 생성자 프로퍼티 타입을 해당 Repository로 변경
- [x] Repository 객체를 교체하기 위해 또 다른 객체를 만들어 바꿔줘야 한다. 즉, ViewModel에 직접적인 변경사항이 발생한다.
  - ViewModel이 Repository의 변경사항에 영향을 받지 않도록 한다.
    - Repository 인터페이스 정의 및 적용


## 1단계

## 기능 요구 사항

**생성자 주입 - 자동**  
다음 문제점을 해결한다.  

- [x] ViewModel에서 참조하는 Repository가 정상적으로 주입되지 않는다.
  - 각 ViewModel에 대한 Factory 클래스를 생성하고 이를 활용해 Repository를 주입 
- [x] Repository를 참조하는 다른 객체가 생기면 주입 코드를 매번 만들어줘야 한다.
  - [x] ViewModel에 수동으로 주입되고 있는 의존성들을 자동으로 주입되도록 바꿔본다.
    - 해당 ViewModel이 필요한 의존성을 동적으로 찾아서 자동으로 생성 및 주입시키는 방법을 찾아본다.
      - Reflection을 활용하여 런타임에 의존성 정보를 확인하고, 의존성을 자동으로 생성 및 주입시킨다.
  - [x] 특정 ViewModel에서만이 아닌, 범용적으로 활용될 수 있는 자동 주입 로직을 작성한다. (`MainViewModel`, `CartViewModel` 모두 하나의 로직만 참조한다)
  - [x] 100개의 ViewModel이 생긴다고 가정했을 때, 자동 주입 로직 100개가 생기는 것이 아니다. 하나의 자동 주입 로직을 재사용할 수 있어야 한다.
    - 각 ViewModel에 대한 의존성 주입 코드를 공통된 로직 하나로 통일시킬 수 있는 방법을 찾아본다.
      - Reflection을 활용하여 ViewModel에 대한 자동 의존성 주입을 수행하는 공통 Factory 클래스를 작성하였다.
- [x] 장바구니에 접근할 때마다 매번 `CartRepository` 인스턴스를 새로 만들고 있다.
  - [x] 여러 번 인스턴스화 할 필요 없는 객체는 최초 한 번만 인스턴스화 한다. (이 단계에서는 너무 깊게 생각하지 말고 싱글 오브젝트로 구현해도 된다.)
    - 싱글톤, Application 등을 고려하여 최초 한 번 인스턴스화 한 객체를 캐싱하여 저장한다.
      - Application을 활용하여 앱 시작 단계에 필요한 DependencyContainer를 생성 및 의존성을 삽입한다.
      - DependencyContainer의 구현체 내부에서 인스턴스화 한 객체를 캐싱하여 저장한다.

## 선택 요구 사항

- [ ] TDD로 DI 구현
- [x] Robolectric 으로 기능 테스트
- [ ] ViewModel 테스트
- [ ] 모든 도메인 로직, Repository 단위 테스트

## 프로그래밍 요구 사항

- 사전에 주어진 테스트 코드가 모두 성공해야 한다.
- Annotation은 이 단계에서 활용하지 않는다.

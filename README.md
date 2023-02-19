# 🪜 사다리 게임 미션 페어 프로그래밍


##  Pair: 져니 [⛄️](http://github.com/cl8d), 후추 [😎](https://github.com/Combi153)


## ✔️ 기능 요구사항
- **참여자**
  - 참여자 이름
    - [x] 참여자의 이름은 최소 1글자부터 최대 5글자이다.
    - [x] 참여자 이름에 공백이 포함될 수 없다.
  - 참여자들
    - [x] 참여자 이름 입력 시 쉼표를 기준(,)으로 구분한다.
    - [x] 참여자 이름 앞뒤의 공백을 제거한다.
    - [x] 참여자 이름은 중복이 허용되지 않는다.
    - [x] 참여자는 최소 2명 이상부터 가능하다.
    - [ ] 결과 출력 참여자로 ‘all’을 입력받았다면, 전체 참가자를 반환한다.
    - [ ] 결과 출력 참여자로 입력받은 참여자에 존재하는 사람인지 검증하고, 참가자를 반환한다.
- **사다리**
  - [x] 사다리에 대한 정보를 반환한다.  
  - [ ] 입력받은 참가자의 순서대로 사다리 타기를 진행한다.
  - [ ] 사다리의 각 행에 대해, 각각의 가로대(열)가 존재하는지를 체크한다.
    - 만약 가로대가 존재한다면, 다음 행의 다음 가로대(열)로 이동한다.
    - 가로대가 존재하지 않는다면, 다음 행의 같은 가로대(열)로 이동한다.
  - [ ] 마지막 사다리 행이라면 (사다리 높이만큼 이동했다면), 현재 몇 번째 가로대 (열)에 존재하는지를 반환한다.
- **사다리 생성기**
  - [x] 최대 사다리 높이를 입력받는다.
  - [x] 최대 사다리의 높이는 정수값만 입력할 수 있다.
  - [x] 최대 사다리 높이는 1 이상부터 10,000까지다.
  - [x] 사다리 높이만큼 사다리 가로대를 만들어서, 사다리 객체를 생성한다.
- **사다리 가로대들**
  - [x] 왼쪽 사다리 가로대가 존재하지 않으면, 사다리 가로대 생성기의 값을 사다리 가로대에게 전달한다.
  - [x] 사다리의 가로 라인은 이어지지 않는다.
- **사다리 가로대**
  - [x] 가로대를 생성할지에 대한 여부를 리턴한다.
- **사다리 가로대 생성기**
  - [x] true 혹은 false의 값을 생성한다.
- **사다리 결과**
  - [x] 사다리 결과는 쉼표(,)로 구분하여 입력받는다.
  - [x] 사다리 결과의 수가 참여자의 수와 동일한지 검증한다.
  - [ ] 참여자에 대해, 사다리 게임의 결과로 반환받은 열의 값과 동일한 순서로 입력받은 실행 결과를 반환한다.
- **사다리 결과 이름**
  - [x] 사다리 결과 이름은 공백이 들어올 수 없다. 
- **출력**
  - [x] 사다리의 가로 라인의 폭은 5로 고정한다.
  - [x] 사다리 출력 시 사람 이름도 함께 출력한다.
  - [x] 사다리 게임 실행 순서는 입력받은 참여자 이름 순서를 따른다.
  - [ ] 결과를 보고 싶은 사람이 한 명의 사람이면 단일 결과를 출력한다.
  - [ ] 결과를 보고 싶은 사람이 전체 사용자라면 콜론(:)을 통해 이름과 결과를 구분하여 출력한다.



- **💡추후 고려사항**
  - 여러 명의 이름을 쉼표(,)로 구분하여 입력받고, 실행 결과를 출력한다.
      - 전체 사용자의 실행 결과를 출력하는 것과 동일한 형식으로 출력한다.


## ✔️ 도메인 설계
  - **참여자**
    - Participant 
    - ParticipantName
  - **참여자들**
    - Participants
  - **사다리**
    - Ladder
  - **사다리 생성기**
    - LadderFactory
  - **사다리 가로대들**
    - Rungs
  - **사다리 가로대**
    - Rung
  - **사다리 가로대 생성기**
    - RungGenerator
  - **사다리 결과**
    - LadderResult

## ✔️ 프로그래밍 요구사항
- indent는 최대 1까지 허용한다.
- 메서드의 길이는 최대 10라인까지 허용한다.
- 모든 기능은 TDD로 구현한다. (UI 로직은 제외한다.)
- 배열을 사용하지 않고 컬렉션을 사용한다.
- 모든 원시값과 문자열을 포장한다.
- Enum을 적용한다.
- 줄여쓰지 않는다.
- 일급 컬렉션을 적용한다.
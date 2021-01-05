ML using mobile
- Tensorflow Lite: 오픈소스 딥러닝 프레임워크
    * Android Studio 4.1 이상에서 Tensorflow Lite Model을 import 가능함 (.tflite)
    * 스타일 전이도 가능한 것으로 보임. 사전 학습된 tflite로 스타일 전송을 적용 가능. (오픈 소스), 모델 재훈련 가능
    * 구조: Style Predict model을 통해서 style을 학습한 다음 Style transform model을 통해서 적용함.
    * 문제1: 기존 계획서에는 서버를 활용할 예정이었음 => 변경이 필요할 수 있다.
    * 문제2: 정확도가 충분한지 여부를 검토해야 한다.
    * 문제3: (설마하는 걱정이지만) 독자 구현 이외에 어떤 것도 활용하면 안되는 경우 (이 경우는 시간 소요가 더 심각해짐)
    * TODO: model 구현을 어떻게 할지와 Android 앱 구조 구현을 어떻게 할지 결정이 필요함.
      * Android 앱 Test 구현을 먼저 한 다음, 추가로 적용하는 것이 필요함.
      * 2주 이내에 Test application을 구현하는 것을 목표로 함.
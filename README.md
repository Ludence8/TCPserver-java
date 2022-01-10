# TCPserver-java  
------------  
## 개발 목적  
------------  
너무나도 갑작스럽게 진행된 내 첫번째 프로그래밍 실제 연습이자 프로젝트.  
친했던 중학교 친구의 약 5년만에 온 연락에서 시작했던 프로젝트였다. 이유는 아르바이트중인 학원에서 학원생들의 컴퓨터를 누가 언제 사용하는지에 대한 로그를 남김과 동시에 어디 컴퓨터가 사용하지 않고 있는지 확인이 목적이였다고...  
듣다보니 나도 직접 하면서 실력도 늘 수 있을 것 같고 워낙 착했던 친구의 부탁이라 시작.  

## 사용 언어  
------------  
일단은 java로 Socket 통신으로 작업해볼까 한다. 1학년 2학기에 배운 내용을 응용해보면 나름 괜찮은 프로그램을 만들어볼 수 있을 것 같다는 생각이 들어서였다.  
하지만 처음 하기도 하고 혼자 하는 프로젝트이기에 어떻게 될지는 미지수....  

## 개발 기록  
------------  
### 12/26 이전  
25일에 이야기한 내용 바탕으로 기본적인 서버 구성. JOptionPane을 통해 UI 만들고자 함.  
### 12/27  
검색 중 JOptionPane와 JFrame를 함께 섞어서 사용하면 괜찮지 않을까라는 생각에 testing이란 파일을 새로 만들어 시작.  
그런데 생각보다 상태가 좋지 않음. 처음 로그를 남기고 일정 시간이 지나면 다른 창이 뜨고, 그 창에서 일정 시간 입력이 없으면 꺼지고 다시 실행되는 부분은 만듦.  
하지만 그 과정에서 입력을 주고 버튼을 누르면 처음으로 돌아가버리고 자동으로 퇴장 로그를 남겨버리는 문제 발생. 어떻게 해야할지 잘 모르겠다. 어쩌면 JFrame를 기본으로 잡고 JOptionPane으론 Message만 주고 일정시간 후 꺼지게 하는 방법이 더 낫지 않을까라 생각하지만 머리가 아프므로 잠시 보류. testing.java는 아무거나 일단 때려박듯이 코드를 작성해서 코드가 읽기 상당히 힘들어짐. 이 부분 역시 빨리 고쳐야 할 부분이라고 생각함.  
### 12/28  
갈아엎을 방법 연구 시작. 새 파일을 만들어서 JOptionPane을 메인으로 사용하는게 아니라 JFrame을 메인으로 잡고 다시 개발을 시작했다. 몇 번씩 갈아엎고 갈아엎으니 생각보다 멘탈이 아픔. 그래도 생각보다 많이 작업했다고 생각. 일단은 서버와 연동은 생각하지 않고 UI 구현 및 종료 버튼 처리, 이름 입력시 JTextArea에 올리기 등 구현함.  
(P.S.정보를 조금 더 알아내야 할 듯함..)  
### 12/29  
꽤나 성공적으로 완료. 우연치 않게 가위바위보를 하는 프로그램에서 아이디어를 얻고 꽤나 비슷하게 구현. 버그 등의 문제는 지속적으로 테스트를 해야하기도 하고 아직 소켓 연결은 하지 않았지만 이러한 부분은 금방 할 수 있을 것 같다고 생각한다. 이제 Server에서 시각화 작업을 해 주어야 할 것 같다. 하지만 이 부분 역시 Client 부분에서 일부 차용해오면 될 것 같다고 생각함.  
### 12/30  
ClientTest 파일에 이식, Socket 연결 후 Server와 정상적으로 연결 및 파일 작성까지 되는 것 확인. 얼추 Client부분은 IP만 제 IP로 바꾸고 JFrame에서 제목만 추가해주면 될 것 같다. 이제 Server 작업 시작 예정  
### 12/31  
Client랑 Server 작업 분류 준비중. 새해 맞이 잠시 휴식...  
### 1/1  
서버 부분 작업 시작. 서버 역시 JFrame으로 작업. Client에서 보내는 내용이 정상적으로 TextArea에 표시되는 것 확인. 이제 컴퓨터별로 번호를 부여하는 법이랑 여러개 컴퓨터로 동시 접속을 했을 때 작동이 잘 되는지, 그리고 exe화하는법 등만 해결하면 1차적으로는 완성이 될 것 같음  
### 1/8  
클라이언트 전달 방법 및 전체적 UI 수정. 또한 종료를 클릭할 시 나오는 오류 해결. 날짜 표현 24시로 변환. 서버 UI에서 보이는 부분 수정.  
### 1/10  
서버, 클라이언트 모두 X키를 누를 때 프로그램이 종료되지 않는 오류 수정. jar, exe파일 모두 실행 시도중. 대부분 잘 실행되지만 Client가 처음 실행하면 이상하게 바로 죽고 다시 키면 또 제대로 작동되는 현상이 있음. 이부분에 대한 해결이 필요. 이후 여러 컴퓨터에서 작동되고 정상적으로 작동되는지 확인하면 완성될 것 같다.

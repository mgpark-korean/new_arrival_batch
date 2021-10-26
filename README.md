# new_arrival_batch

## 기능 설명
[스투시 신상품](https://www.stussy.co.kr/collections/new-arrivals) 정보를  selenium으로 크롤링 후 DB에 데이터 insert 후 DB 데이터를 CSV,Excel로 형식에 맞춰 생성

## Spring Batch 구성
 jobName : crawlingJob. 
 
 step: [crawlingJob, createCsvStep, createExcelStep]. 
 
    > crawlingJob : 스투시 신상품 크롤링 step <br>
    > createCsvStep : CSV 생성 step <br>
    > createExcelStep : Excel 생성 step <br>

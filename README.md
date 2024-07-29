# T09 Transcript With Database

Pada sesi ini anda diminta untuk kembali mengerjakan T08 Transcript dengan database sebagai media penyimpanan. Anda tidak diperkenankan menyimpan data pada solusi. Semua data diletakkan dan diambil dari database. Penyimpanan data pada solusi mendiskualifikasi anda dari penilaian.

## Transcript (academic.driver.Driver1, 100pts)

Pada sebuah transkrip tertuang daftar mata kuliah yang pernah ditempuh oleh seorang mahasiswa selama studi. Pada transkrip:
1. Mata kuliah ditampilkan secara historis. Mata kuliah yang diselesaikan pada tahun ajaran 2020/2021 akan mendahului mata kuliah yang diselesaikan pada tahun ajaran 2021/2022. Demikian juga berlaku untuk periode semester.
2. Bila dilakukan pengambilan suatu mata kuliah lebih dari satu kali, maka hanya pencapaian pada pengambilan terakhir akan ditampilkan.

Berikut adalah format perintah yang digunakan.

```bash
student-transcript#<student-id>
```

Simulator kemudian akan menampilkan performa mahasiswa secara kumulatir dan diikuti dengan informasi performa mahasiswa untuk setiap pengambilan mata kuliah (```enrollment```). Perhatikan contoh berikut.

**Input**:

```bash
lecturer-add#0130058501#Parmonangan Rotua Togatorop#PAT#mona.togatorop@del.ac.id#Information Systems
lecturer-add#0114129002#Iustisia Natalia Simbolon#IUS#iustisia.simbolon@del.ac.id#Informatics
lecturer-add#0124108201#Rosni Lumbantoruan#RSL#rosni@del.ac.id#Information Systems
course-add#12S1101#Dasar Sistem Informasi#3#D
course-add#12S2102#Basisdata#4#C 
student-add#12S20001#Marcelino Manalu#2020#Information Systems
student-add#12S20002#Yoga Sihombing#2020#Information Systems
student-add#12S20003#Marcel Simanjuntak#2020#Information Systems
course-open#12S1101#2020/2021#odd#IUS
enrollment-add#12S1101#12S20001#2020/2021#odd
enrollment-add#12S1101#12S20002#2020/2021#odd
enrollment-add#12S1101#12S20003#2020/2021#odd
enrollment-grade#12S1101#12S20001#2020/2021#odd#B
enrollment-remedial#12S1101#12S20001#2020/2021#odd#B
enrollment-grade#12S1101#12S20002#2020/2021#odd#B
enrollment-grade#12S1101#12S20003#2020/2021#odd#B
student-details#12S20003
enrollment-remedial#12S1101#12S20003#2020/2021#odd#AB
course-open#12S2102#2021/2022#odd#PAT,IUS,RSL
enrollment-add#12S2102#12S20001#2021/2022#odd
enrollment-add#12S2102#12S20002#2021/2022#odd
enrollment-add#12S2102#12S20003#2021/2022#odd
enrollment-grade#12S2102#12S20001#2021/2022#odd#B
enrollment-grade#12S2102#12S20002#2021/2022#odd#AB
enrollment-grade#12S2102#12S20003#2021/2022#odd#BC
student-details#12S20001
course-open#12S2102#2022/2023#odd#IUS,RSL
enrollment-add#12S2102#12S20003#2022/2023#odd
enrollment-grade#12S2102#12S20003#2022/2023#odd#AB
enrollment-remedial#12S2102#12S20003#2022/2023#odd#B
student-details#12S20001
student-details#12S20002
student-transcript#12S20003
---

```

**Output**:

```bash
12S20003|Marcel Simanjuntak|2020|Information Systems|3.00|3
12S20001|Marcelino Manalu|2020|Information Systems|3.00|7
12S20001|Marcelino Manalu|2020|Information Systems|3.00|7
12S20002|Yoga Sihombing|2020|Information Systems|3.29|7
12S20003|Marcel Simanjuntak|2020|Information Systems|3.21|7
12S1101|12S20003|2020/2021|odd|AB(B)
12S2102|12S20003|2022/2023|odd|B(AB)
0130058501|Parmonangan Rotua Togatorop|PAT|mona.togatorop@del.ac.id|Information Systems
0114129002|Iustisia Natalia Simbolon|IUS|iustisia.simbolon@del.ac.id|Informatics
0124108201|Rosni Lumbantoruan|RSL|rosni@del.ac.id|Information Systems
12S1101|Dasar Sistem Informasi|3|D
12S2102|Basisdata|4|C
12S20001|Marcelino Manalu|2020|Information Systems
12S20002|Yoga Sihombing|2020|Information Systems
12S20003|Marcel Simanjuntak|2020|Information Systems
12S1101|12S20001|2020/2021|odd|B(B)
12S1101|12S20002|2020/2021|odd|B
12S1101|12S20003|2020/2021|odd|AB(B)
12S2102|12S20001|2021/2022|odd|B
12S2102|12S20002|2021/2022|odd|AB
12S2102|12S20003|2021/2022|odd|BC
12S2102|12S20003|2022/2023|odd|B(AB)

```

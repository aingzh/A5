<template>
    <el-row>
        <el-col :span="6">
            <div style="margin-top:25px;text-align: centers;">
                <div @click="toPage('/BorrowHistory')">
                    <div class="round1">
                        <div style="position:relative;top:50%;text-align: center">
                            BorrowHistory
                        </div>
                    </div>
                </div>
            </div>

            <div style="margin-top: 30px;">
                <div class="bookBox1">
                    <div class="bookPicture">
                        <img :src="picture1" @click="toPage('/Book1')">
                    </div>

                    <div class="bookName">
                        bookName
                    </div>

                    <div class="bookTxt">
                        txt:book1
                    </div>
                </div>
            </div>
        </el-col>

        <el-col :span="6">
            <div style="margin-top:200px; text-align: center;">
                <div @click="toPage('/BookReturn')">
                    <div class="round2">
                        <div style="position:relative;top:50%;text-align: center;">
                            Book Return
                        </div>
                    </div>
                </div>
            </div>

            <div style="margin-top: 30px;">
                <div class="bookBox2">
                    <div class="bookPicture">
                        <img :src="picture2" @click="toPage('/Book2')">
                    </div>

                    <div class="bookName">
                        bookName
                    </div>

                    <div class="bookTxt">
                        txt:book2
                    </div>
                </div>
            </div>
        </el-col>

        <el-col :span="6">
            <div style="margin-top:150px; text-align: center;">
                <div @click="toPage('/MyAppointment')">
                    <div class="round3">
                        <div style="position:relative;top:50%;text-align: center;">
                            My Appointment
                        </div>
                    </div>
                </div>
            </div>
            <div style="margin-top: 30px;">
                <div class="bookBox3">
                    <div class="bookPicture">
                        <img :src="picture3" @click="toPage('/Book3')">
                    </div>

                    <div class="bookName">
                        bookName
                    </div>

                    <div class="bookTxt">
                        txt:book3
                    </div>
                </div>
            </div>
        </el-col>

        <el-col :span="6">
            <div style="margin-top:50px; text-align: center;">
                <div @click="toPage('/PersonalSpace')">
                    <div class="round4">
                        <div style="position:relative; top:50%;text-align: center;">
                            PersonalSpace
                        </div>
                    </div>
                </div>
            </div>

            <div style="margin-top: 30px;">
                <div class="bookBox4">
                    <div class="bookPicture">
                        <img :src="picture4" @click="toPage('/Book4')">
                    </div>

                    <div class="bookName">
                        bookName
                    </div>

                    <div class="bookTxt">
                        txt:book3
                    </div>
                </div>
            </div>
        </el-col>
    </el-row>
</template>
<script lang = "ts">
import { dataType } from 'element-plus/es/components/table-v2/src/common';

export default {
    name: "Buttons",
    methods: {
        toPage(pagePath: string) {
            (this as any).$router.push(pagePath)
        }
    },
    data(){
        return{
            picture1:require('../assets/bookpicture1.jpg'),
            picture2:require('../assets/bookpicture2.jpg'),
            picture3:require('../assets/bookpicture3.jpg'),
            picture4:require('../assets/bookpicture4.jpg')
        }
    }
}
</script>

<style>

.bookBox1 {
    width: 210px;
    height: 360px;
    /*border: 4px solid rgb(102, 172, 238);*/
    margin: auto;
    padding-top: 10px;
    background: rgba(182, 217, 255, 0.8);
    box-shadow: 3px 3px 10px rgb(74, 74, 74);
}

.bookBox2 {
    width: 166px;
    height: 229px;
    /*border: 4px solid rgb(102, 172, 238);*/
    margin: auto;
    padding-top: 10px;
    background: rgba(182, 217, 255, 0.8);
    box-shadow: 3px 3px 10px rgb(74, 74, 74);
}

.bookBox3{
    width: 166px;
    height: 279px;
    /*border: 4px solid rgb(102, 172, 238);*/
    margin: auto;
    padding-top: 10px;
    background: rgba(182, 217, 255, 0.8);
    box-shadow: 3px 3px 10px rgb(74, 74, 74);
}

.bookBox4 {
    width: 210px;
    height: 335px;
    /*border: 4px solid rgb(102, 172, 238);*/
    margin: auto;
    padding-top: 10px;
    background: rgba(182, 217, 255, 0.8);
    box-shadow: 3px 3px 10px rgb(74, 74, 74);
}

.bookPicture {
    width: 90px;
    height: 110px;
    box-shadow: 3px 3px 10px rgb(74, 74, 74);
    margin: auto;
}

.bookName {
    margin: auto;
    padding: 10px;
    text-align: center;
}

.bookTxt {
    text-align: center
}

.round1 {
    width: 190px;
    height: 190px;
    border: 15px solid white;
    border-radius: 50%;
    margin: auto;
    background: rgb(179, 189, 216);
    box-shadow: 3px 3px 10px rgb(74, 74, 74)
}

.round2 {
    width: 150px;
    height: 150px;
    border: 13px solid white;
    border-radius: 50%;
    margin: auto;
    background: rgb(242, 223, 223);
    box-shadow: 3px 3px 10px rgb(74, 74, 74)
}

.round3 {
    width: 150px;
    height: 150px;
    border: 13px solid white;
    border-radius: 50%;
    margin: auto;
    background: rgb(237, 226, 215);
    box-shadow: 3px 3px 10px rgb(74, 74, 74)
}

.round4 {
    width: 190px;
    height: 190px;
    border: 15px solid white;
    border-radius: 50%;
    margin: auto;
    background: rgb(167, 209, 223);
    box-shadow: 3px 3px 10px rgb(74, 74, 74)
}
</style>

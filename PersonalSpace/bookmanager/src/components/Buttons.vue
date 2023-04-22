<template>
    <el-row>
        <el-col :span="6">
            <div style="margin-top:30px; text-align: centers;">
                <div @click="toPage('/BorrowHistory')">
                    <div class="round1">
                        <div style="position:relative;top:50%;text-align: center">
                            BorrowHistory
                        </div>
                    </div>
                </div>
            </div>

            <div style="margin-top:200px; text-align: center;">
                txt:book1
            </div>
        </el-col>

        <el-col :span="6">
            <div style="margin-top:100px; text-align: center;">
                <div @click="toPage('/BookReturn')">
                    <div class="round2">
                        <div style="position:relative;top:50%;text-align: center;">
                            Book Return
                        </div>
                    </div>
                </div>
            </div>

            <div style="margin-top:200px; text-align: center;">
                txt:book2
            </div>
        </el-col>

        <el-col :span="6">
            <div style="margin-top:100px; text-align: center;">
                <div @click="toPage('/MyAppointment')">
                    <div class="round3">
                        <div style="position:relative;top:50%;text-align: center;">
                            My Appointment
                        </div>
                    </div>
                </div>
            </div>

            <div style="margin-top:200px; text-align: center;">
                txt:book4
            </div>
        </el-col>

        <el-col :span="6">
            <div style="margin-top:30px; text-align: center;">
                <div @click="toPage('/PersonalSpace')">
                    <div class="round4">
                        <div style="position:relative; top:50%;text-align: center;">
                            PersonalSpace
                        </div>
                    </div>
                </div>
            </div>

            <div style="margin-top:200px; text-align: center;">
                txt:book4
            </div>
        </el-col>
    </el-row>
</template>
<script lang = "ts">
export default {
    name: "Buttons",
    methods: {
        toPage(pagePath: string) {
            (this as any).$router.push(pagePath)
        }
    }
}
</script>

<style>
.round1 {
    width: 150px;
    height: 150px;
    border: 15px solid white;
    border-radius: 50%;
    margin: auto;
    background: rgb(179, 189, 216);
    box-shadow: 3px 3px 10px rgb(179, 189, 216)
}

.round2 {
    width: 150px;
    height: 150px;
    border: 15px solid white;
    border-radius: 50%;
    margin: auto;
    background: rgb(242, 223, 223);
    box-shadow: 3px 3px 10px rgb(242, 223, 223)
}

.round3 {
    width: 150px;
    height: 150px;
    border: 15px solid white;
    border-radius: 50%;
    margin: auto;
    background: rgb(237, 226, 215);
    box-shadow: 3px 3px 10px rgb(237, 226, 215)
}

.round4 {
    width: 150px;
    height: 150px;
    border: 15px solid white;
    border-radius: 50%;
    margin: auto;
    background: rgb(167, 209, 223);
    box-shadow: 3px 3px 10px rgb(167, 209, 223)
}
</style>

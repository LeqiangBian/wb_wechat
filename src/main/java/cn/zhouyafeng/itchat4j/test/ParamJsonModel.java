package cn.zhouyafeng.itchat4j.test;

import java.io.Serializable;

public class ParamJsonModel implements Serializable {


    /**
     * reqType : 0
     * perception : {"inputText":{"text":"附近的酒店"},"inputImage":{"url":""},"inputMedia":{"url":""},"selfInfo":{"location":{"city":"北京","province":"北京","street":"信息路"}}}
     * userInfo : {"apiKey":"cefc46bb55914364a6fb8f9544062714","userId":"123456"}
     *
     * bca808aec5ca43ad9da31333da932f71
     */

    public static String modelJosn = "{\n" +
            "\t\"reqType\":0,\n" +
            "    \"perception\": {\n" +
            "        \"inputText\": {\n" +
            "            \"text\": \"\"\n" +
            "        },\n" +
            "        \"inputImage\": {\n" +
            "            \"url\": \"\"\n" +
            "        },\n" +
            "\t\"inputMedia\": {\n" +
            "            \"url\": \"\"\n" +
            "        },\n" +
            "        \"selfInfo\": {\n" +
            "            \"location\": {\n" +
            "                \"city\": \"北京\",\n" +
            "                \"province\": \"北京\",\n" +
            "                \"street\": \"中关村新东方\"\n" +
            "            }\n" +
            "        }\n" +
            "    },\n" +
            "    \"userInfo\": {\n" +
            "        \"apiKey\": \"bca808aec5ca43ad9da31333da932f71\",\n" +
            "        \"userId\": \"123456\"\n" +
            "    }\n" +
            "}";

    private int reqType;
    private PerceptionBean perception;
    private UserInfoBean   userInfo;


    public int getReqType() {
        return reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

    public PerceptionBean getPerception() {
        return perception;
    }

    public void setPerception(PerceptionBean perception) {
        this.perception = perception;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class PerceptionBean {
        /**
         * inputText : {"text":"附近的酒店"}
         * inputImage : {"url":""}
         * inputMedia : {"url":""}
         * selfInfo : {"location":{"city":"北京","province":"北京","street":"信息路"}}
         */

        private InputTextBean inputText;
        private InputImageBean inputImage;
        private InputMediaBean inputMedia;
        private SelfInfoBean   selfInfo;

        public InputTextBean getInputText() {
            return inputText;
        }

        public void setInputText(InputTextBean inputText) {
            this.inputText = inputText;
        }

        public InputImageBean getInputImage() {
            return inputImage;
        }

        public void setInputImage(InputImageBean inputImage) {
            this.inputImage = inputImage;
        }

        public InputMediaBean getInputMedia() {
            return inputMedia;
        }

        public void setInputMedia(InputMediaBean inputMedia) {
            this.inputMedia = inputMedia;
        }

        public SelfInfoBean getSelfInfo() {
            return selfInfo;
        }

        public void setSelfInfo(SelfInfoBean selfInfo) {
            this.selfInfo = selfInfo;
        }

        public static class InputTextBean {
            /**
             * text : 附近的酒店
             */

            private String text;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }

        public static class InputImageBean {
            /**
             * url :
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class InputMediaBean {
            /**
             * url :
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class SelfInfoBean {
            /**
             * location : {"city":"北京","province":"北京","street":"信息路"}
             */

            private LocationBean location;

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public static class LocationBean {
                /**
                 * city : 北京
                 * province : 北京
                 * street : 信息路
                 */

                private String city = "北京";
                private String province = "北京";
                private String street = "中关村新东方";

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getStreet() {
                    return street;
                }

                public void setStreet(String street) {
                    this.street = street;
                }
            }
        }
    }

    public static class UserInfoBean {
        /**
         * apiKey : cefc46bb55914364a6fb8f9544062714
         * userId : 123456
         */

        private String apiKey = "cefc46bb55914364a6fb8f9544062714";
        private String userId = "123456";

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
val SimpleJsonObjectString =
    """{"receipt":[{"hahaha": null},{"ok":true},{"textAlign":1},{"font":0},{"text":"Company name\nStore Store Name"},{"textAlign":0},{"text":"\n"},{"textAlign":1},{"font":0},{"text":"\n\n"},{"textSize":1,"textSizeHeight":1},{"textAlign":0},{"font":0},{"text":"Invoice no.                15-15\nInvoice ID              81779fbf\nDate            27.10.2016 14:46\nService                    Admin\n"},{"font":0},{"text":"________________________________\n\nLorem Ipsum is simply      12.00\ndummy text of the printing      \nand typesetting industry.       \nFirst product             123.00\nCustom                      3.00\n________________________________\n\n"},{"textSize":2,"textSizeHeight":2},{"textAlign":2},{"text":"Total CHF 138.00\n\n"},{"textSize":1,"textSizeHeight":1},{"font":0},{"textAlign":0},{"text":"Cash CHF                  200.00\nChange CHF                 62.00\n\nVAT no. CHE- 123.456.789\nRate       Net     VAT     Gross\n8.00%   127.78   10.22    138.00\n\n\n"},{"cut":"Nothing"}]}"""

val JsonArrayWithNullableValuesString = """[
    {
        "deleted": false,
        "name": "Kaffeemaschine",
        "positionInParentProductCategory": 0,
        "uuid": "4b002482-d3bc-41de-b4fc-17c120090e75",
        "id": 364515,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "Kaffeemaschine",
        "description": "Eine Kaffeemaschine ist ein Gerät zur Herstellung von Kaffee. Dieser entsteht durch thermische Extraktion und Filtration von gemahlenen Kaffeebohnen (Heißextraktion) mit Wasser als Extraktionsmittel.\n                Heute verbreitete Formen sind die Filterkaffeemaschine, der Kaffeevollautomat und die Espressomaschine, welche verschiedene Filterelemente einsetzen; Einweg-Kaffeefilter, Metallsiebe oder -Kaffeepads (für entsprechend ausgestattete Maschinen).",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "Kaffeemaschine",
        "metaDescription": "Eine Kaffeemaschine ist ein Gerät zur Herstellung von Kaffee. Dieser entsteht durch thermische Extraktion und Filtration von gemahlenen Kaffeebohnen (Heißextrak",
        "virtualPrinter": null,
        "bgColor": "hsla(167.2072072072072,33%,65%,1)",
        "parentCategoryUuid": null
    },
    {
        "deleted": false,
        "image": {
            "uuid": "7df302f4-92c4-421d-91a9-21701e0682bf",
            "thumbs": [
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/57b60c84-03da-400f-af9f-3a2549e13ab2/1200_1200.JPEG",
                    "size": "1200x1200"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/57b60c84-03da-400f-af9f-3a2549e13ab2/600_600.JPEG",
                    "size": "big"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/57b60c84-03da-400f-af9f-3a2549e13ab2/160_160.JPEG",
                    "size": "medium"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/57b60c84-03da-400f-af9f-3a2549e13ab2/300_300.JPEG",
                    "size": "normal"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/57b60c84-03da-400f-af9f-3a2549e13ab2/original",
                    "size": "original"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/57b60c84-03da-400f-af9f-3a2549e13ab2/50_50.JPEG",
                    "size": "small"
                }
            ]
        },
        "name": "Tee",
        "positionInParentProductCategory": 0,
        "uuid": "8dc2ff9d-0848-4e52-960c-8bfe80563c3b",
        "id": 364516,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "Tee",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "Tee",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(10.33033033033033,32%,65%,1)",
        "parentCategoryUuid": null
    },
    {
        "deleted": false,
        "image": {
            "uuid": "20de292f-b8bd-4aac-96f7-fbd2d7057698",
            "thumbs": [
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/de42f01d-693a-4857-8dd5-d58a94caa635/1200_1200.JPEG",
                    "size": "1200x1200"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/de42f01d-693a-4857-8dd5-d58a94caa635/600_600.JPEG",
                    "size": "big"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/de42f01d-693a-4857-8dd5-d58a94caa635/160_160.JPEG",
                    "size": "medium"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/de42f01d-693a-4857-8dd5-d58a94caa635/300_300.JPEG",
                    "size": "normal"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/de42f01d-693a-4857-8dd5-d58a94caa635/original",
                    "size": "original"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/de42f01d-693a-4857-8dd5-d58a94caa635/50_50.JPEG",
                    "size": "small"
                }
            ]
        },
        "name": "Lifestyle Produkte",
        "positionInParentProductCategory": 0,
        "uuid": "34251979-c9a6-43c2-9bdd-92e4b9ae6c62",
        "id": 364517,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "Lifestyle Produkte",
        "description": "Our t-shirts and cups :)",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "Lifestyle Produkte",
        "metaDescription": "Our t-shirts and cups :)",
        "virtualPrinter": null,
        "bgColor": "hsla(221.5015015015015,37%,65%,1)",
        "parentCategoryUuid": null
    },
    {
        "deleted": false,
        "name": "Caffè",
        "positionInParentProductCategory": 0,
        "uuid": "f49c3e13-72b2-43c4-80e9-7af4accaf3e2",
        "id": 364518,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "Caffè",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "Caffè",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(52.612612612612615,45%,65%,1)",
        "parentCategoryUuid": null
    },
    {
        "deleted": false,
        "name": "Desserts & Snacks",
        "positionInParentProductCategory": 0,
        "uuid": "9068b919-6382-4b6f-a60f-5e949a1e9072",
        "id": 364519,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "Desserts & Snacks",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "Desserts & Snacks",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(212.85285285285286,37%,65%,1)",
        "parentCategoryUuid": null
    },
    {
        "deleted": false,
        "name": "Location     Latin America",
        "parentProductCategory": {
            "uuid": "f49c3e13-72b2-43c4-80e9-7af4accaf3e2"
        },
        "positionInParentProductCategory": 0,
        "uuid": "57a2cbd2-e3a5-4194-bac3-70bde31233b5",
        "id": 364520,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "Location     Latin America",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "Location     Latin America",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(214.05405405405406,31%,65%,1)",
        "parentCategoryUuid": "f49c3e13-72b2-43c4-80e9-7af4accaf3e2"
    },
    {
        "deleted": false,
        "name": "Location    Africa",
        "parentProductCategory": {
            "uuid": "f49c3e13-72b2-43c4-80e9-7af4accaf3e2"
        },
        "positionInParentProductCategory": 0,
        "uuid": "d3ecd048-5f55-486c-86ba-6481d810be55",
        "id": 364521,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "Location    Africa",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "Location    Africa",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(163.84384384384384,44%,65%,1)",
        "parentCategoryUuid": "f49c3e13-72b2-43c4-80e9-7af4accaf3e2"
    },
    {
        "deleted": false,
        "name": "Location    Indonesia",
        "parentProductCategory": {
            "uuid": "f49c3e13-72b2-43c4-80e9-7af4accaf3e2"
        },
        "positionInParentProductCategory": 0,
        "uuid": "3dc6f61a-2dda-489a-b003-97a29d83e885",
        "id": 364522,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "Location    Indonesia",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "Location    Indonesia",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(122.76276276276276,45%,65%,1)",
        "parentCategoryUuid": "f49c3e13-72b2-43c4-80e9-7af4accaf3e2"
    },
    {
        "deleted": false,
        "name": "Organic",
        "parentProductCategory": {
            "uuid": "f49c3e13-72b2-43c4-80e9-7af4accaf3e2"
        },
        "positionInParentProductCategory": 0,
        "uuid": "ec09a9f7-a2fa-4a4a-a145-08ceba67e6f8",
        "id": 364523,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "Organic",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "Organic",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(215.4954954954955,46%,65%,1)",
        "parentCategoryUuid": "f49c3e13-72b2-43c4-80e9-7af4accaf3e2"
    },
    {
        "deleted": false,
        "name": "Espresso",
        "parentProductCategory": {
            "uuid": "f49c3e13-72b2-43c4-80e9-7af4accaf3e2"
        },
        "positionInParentProductCategory": 0,
        "uuid": "05672656-2785-4829-98fc-e3de4f65bb46",
        "id": 364524,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "Espresso",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "Espresso",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(79.75975975975976,30%,65%,1)",
        "parentCategoryUuid": "f49c3e13-72b2-43c4-80e9-7af4accaf3e2"
    },
    {
        "deleted": false,
        "image": {
            "uuid": "1c3e1775-1a0b-4e3d-9b24-f038f547e876",
            "thumbs": [
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/681264cc-4dca-4725-8f32-122360542f35/1200_1200.JPEG",
                    "size": "1200x1200"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/681264cc-4dca-4725-8f32-122360542f35/600_600.JPEG",
                    "size": "big"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/681264cc-4dca-4725-8f32-122360542f35/160_160.JPEG",
                    "size": "medium"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/681264cc-4dca-4725-8f32-122360542f35/300_300.JPEG",
                    "size": "normal"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/681264cc-4dca-4725-8f32-122360542f35/original",
                    "size": "original"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/681264cc-4dca-4725-8f32-122360542f35/50_50.JPEG",
                    "size": "small"
                }
            ]
        },
        "name": "Bodum",
        "parentProductCategory": {
            "uuid": "4b002482-d3bc-41de-b4fc-17c120090e75"
        },
        "positionInParentProductCategory": 0,
        "uuid": "381705c6-0aee-4e36-94b0-a2496766df73",
        "id": 364525,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "Bodum",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "Bodum",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(131.41141141141142,42%,65%,1)",
        "parentCategoryUuid": "4b002482-d3bc-41de-b4fc-17c120090e75"
    },
    {
        "deleted": false,
        "image": {
            "uuid": "59c2ed36-c3da-4f8b-b682-e9590efdb76b",
            "thumbs": [
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/ec2fdd60-a331-4c3d-b6d0-9fe0651a7e2f/1200_1200.JPEG",
                    "size": "1200x1200"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/ec2fdd60-a331-4c3d-b6d0-9fe0651a7e2f/600_600.JPEG",
                    "size": "big"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/ec2fdd60-a331-4c3d-b6d0-9fe0651a7e2f/160_160.JPEG",
                    "size": "medium"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/ec2fdd60-a331-4c3d-b6d0-9fe0651a7e2f/300_300.JPEG",
                    "size": "normal"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/ec2fdd60-a331-4c3d-b6d0-9fe0651a7e2f/original",
                    "size": "original"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/ec2fdd60-a331-4c3d-b6d0-9fe0651a7e2f/50_50.JPEG",
                    "size": "small"
                }
            ]
        },
        "name": "BrAun",
        "parentProductCategory": {
            "uuid": "4b002482-d3bc-41de-b4fc-17c120090e75"
        },
        "positionInParentProductCategory": 0,
        "uuid": "f3b09880-4dd6-4a28-b83e-d1fdf571f0bd",
        "id": 364526,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "BrAun",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "BrAun",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(113.3933933933934,43%,65%,1)",
        "parentCategoryUuid": "4b002482-d3bc-41de-b4fc-17c120090e75"
    },
    {
        "deleted": false,
        "image": {
            "uuid": "a9311426-96eb-40e5-9285-80b80cca8875",
            "thumbs": [
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/1200_1200.JPEG",
                    "size": "1200x1200"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/600_600.JPEG",
                    "size": "big"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/160_160.JPEG",
                    "size": "medium"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/300_300.JPEG",
                    "size": "normal"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/original",
                    "size": "original"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/50_50.JPEG",
                    "size": "small"
                }
            ]
        },
        "name": "KRUPS",
        "parentProductCategory": {
            "uuid": "4b002482-d3bc-41de-b4fc-17c120090e75"
        },
        "positionInParentProductCategory": 0,
        "uuid": "bf984868-28a0-4aec-a5aa-403bb8534d06",
        "id": 364527,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "KRUPS",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "KRUPS",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(195.3153153153153,47%,65%,1)",
        "parentCategoryUuid": "4b002482-d3bc-41de-b4fc-17c120090e75"
    },
    {
        "deleted": false,
        "image": {
            "uuid": "0fd5d279-0c1d-4f25-943c-71ec6d939a31",
            "thumbs": [
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/1200_1200.JPEG",
                    "size": "1200x1200"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/600_600.JPEG",
                    "size": "big"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/160_160.JPEG",
                    "size": "medium"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/300_300.JPEG",
                    "size": "normal"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/original",
                    "size": "original"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/50_50.JPEG",
                    "size": "small"
                }
            ]
        },
        "name": "Melitta",
        "parentProductCategory": {
            "uuid": "4b002482-d3bc-41de-b4fc-17c120090e75"
        },
        "positionInParentProductCategory": 0,
        "uuid": "71532208-c066-470e-8cf0-6bd89b4a0e9c",
        "id": 364528,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "Melitta",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "Melitta",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(171.77177177177177,37%,65%,1)",
        "parentCategoryUuid": "4b002482-d3bc-41de-b4fc-17c120090e75"
    },
    {
        "deleted": false,
        "image": {
            "uuid": "f109ad6c-7d1d-42dd-bb0a-5f5fddf48fd1",
            "thumbs": [
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/1200_1200.JPEG",
                    "size": "1200x1200"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/600_600.JPEG",
                    "size": "big"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/160_160.JPEG",
                    "size": "medium"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/300_300.JPEG",
                    "size": "normal"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/original",
                    "size": "original"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/50_50.JPEG",
                    "size": "small"
                }
            ]
        },
        "name": "Russell Hobbs",
        "parentProductCategory": {
            "uuid": "4b002482-d3bc-41de-b4fc-17c120090e75"
        },
        "positionInParentProductCategory": 0,
        "uuid": "5a8096af-0ba1-4739-989a-8c39da311f71",
        "id": 364529,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "Russell Hobbs",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "Russell Hobbs",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(83.84384384384384,39%,65%,1)",
        "parentCategoryUuid": "4b002482-d3bc-41de-b4fc-17c120090e75"
    },
    {
        "deleted": false,
        "image": {
            "uuid": "3455894d-3345-4c62-a42c-53e143f80132",
            "thumbs": [
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/1200_1200.JPEG",
                    "size": "1200x1200"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/600_600.JPEG",
                    "size": "big"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/160_160.JPEG",
                    "size": "medium"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/300_300.JPEG",
                    "size": "normal"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/original",
                    "size": "original"
                },
                {
                    "url": "https://paymash-test.s3.amazonaws.com/companies/23754/a4895842-0799-4c8a-a805-a63c81d3fee1/50_50.JPEG",
                    "size": "small"
                }
            ]
        },
        "name": "WMF",
        "parentProductCategory": {
            "uuid": "4b002482-d3bc-41de-b4fc-17c120090e75"
        },
        "positionInParentProductCategory": 0,
        "uuid": "4ee410a8-0407-4fc4-892d-e41306bb6c14",
        "id": 364530,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "WMF",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "WMF",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(186.9069069069069,42%,65%,1)",
        "parentCategoryUuid": "4b002482-d3bc-41de-b4fc-17c120090e75"
    },
    {
        "deleted": false,
        "name": "Unsere Mischungen",
        "parentProductCategory": {
            "uuid": "f49c3e13-72b2-43c4-80e9-7af4accaf3e2"
        },
        "positionInParentProductCategory": 0,
        "uuid": "adb3ab3f-3bb6-4475-ad95-df2e2251fd7b",
        "id": 364531,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "Unsere Mischungen",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "Unsere Mischungen",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(144.3843843843844,41%,65%,1)",
        "parentCategoryUuid": "f49c3e13-72b2-43c4-80e9-7af4accaf3e2"
    },
    {
        "deleted": false,
        "name": "Decaf",
        "parentProductCategory": {
            "uuid": "f49c3e13-72b2-43c4-80e9-7af4accaf3e2"
        },
        "positionInParentProductCategory": 0,
        "uuid": "68331c56-e04c-493e-888d-615b94469398",
        "id": 364532,
        "visible": true,
        "visibleInWebshop": true,
        "webshopShowImage": false,
        "title": "Decaf",
        "useDifferentWebshopImage": false,
        "positionChangedManually": false,
        "metaTitle": "Decaf",
        "metaDescription": "",
        "virtualPrinter": null,
        "bgColor": "hsla(161.2012012012012,35%,65%,1)",
        "parentCategoryUuid": "f49c3e13-72b2-43c4-80e9-7af4accaf3e2"
    }
]"""
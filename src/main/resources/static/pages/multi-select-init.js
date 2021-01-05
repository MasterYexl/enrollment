//multiselect start

let fidName = document.getElementById("fid-name");
let sedName = document.getElementById("sed-name");
let fid = document.getElementById("fid");
let sed = document.getElementById("sed");
let control = 0;
$('#my_multi_select3').multiSelect({
    selectableHeader: "<input type='text' class='form-control search-input' autocomplete='off' placeholder='搜索...'>",
    selectionHeader: "<div class='form-control search-input'>请至少选择一项</div>",
    keepOrder: true,
    afterInit: function (ms) {
        var that = this,
            $selectableSearch = that.$selectableUl.prev(),
            $selectionSearch = that.$selectionUl.prev(),
            selectableSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selectable:not(.ms-selected)',
            selectionSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selection.ms-selected';

        that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
            .on('keydown', function (e) {
                if (e.which === 40) {
                    that.$selectableUl.focus();
                    return false;
                }
            });

        that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
            .on('keydown', function (e) {
                if (e.which == 40) {
                    that.$selectionUl.focus();
                    return false;
                }
            });
    },
    afterSelect: function (value) {
        if (control>=2) {
            document.getElementById(fid.value+"-selection").click();
            control=1;
        }
        addTag(document.getElementById(value+"-selection").innerText, value);
        control++;
    },
    afterDeselect: function (value) {
        let tmp = --control;
        if (fid.value == value){
            control = 0;
            if (tmp===1) addTag(sedName.innerText, sed.value);
            else addTag("", "");
            control = 1;
            addTag("请选择研究方向", "");
        }
        else if (control===1) addTag("请选择研究方向", "");
        control = tmp;
    }
});

function addTag(text, value){
    if (control === 0){
        fid.value = value;
        fidName.innerText = text;
    }else {
        sed.value = value;
        sedName.innerText = text;
    }
}
function exchangeTag(){
    if (sed.value === null|| sed.value === "") return ;
    let ft = sedName.style.float;
    if (ft === "left"){
        sedName.style.float = "none";
    }else sedName.style.float = "left";
    [fid.value, fidName.innerText, sed.value, sedName.innerText] = [sed.value, sedName.innerText, fid.value, fidName.innerText];
}
function comfier(){
    swal({
        title: "请确认",
        text: "第一志愿: "+fidName.innerText+"\n第二志愿: "+sedName.innerText+"\n提交后将无法再次修改！",
        icon: "warning",
        buttons: ["再想想","确认准确无误"],
        dangerMode: true,
    }).then((value) => {
        if (value) document.getElementById("enroll-form").submit();
    });
}
//multiselect end
import {VsNotification} from "vuesax-alpha";
import {
    CloseCircleBold,
    LikeBold
} from '@vuesax-alpha/icons-vue'
export function NotificationMessage(color,title,msg) {

    let iconItem = null;
    if (color == 'success') {
        iconItem = ``
    }else if (color == 'danger') {
        iconItem = `<vs-icon><CloseCircleBold /></vs-icon>`
    }
    VsNotification({
        icon: iconItem,
        color,
        position: 'top-center',
        title: title,
        content: msg
    })
}

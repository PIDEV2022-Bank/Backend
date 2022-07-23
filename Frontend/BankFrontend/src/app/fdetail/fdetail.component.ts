import {Component, Input, OnInit, Output} from '@angular/core';
import {ForumService} from "../core/service/forum.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-fdetail',
  templateUrl: './fdetail.component.html',
  styleUrls: ['./fdetail.component.css']
})
export class FdetailComponent implements OnInit {

  @Input() viewModel = false;
  @Output()
  currentForum = {
    id: this.route.snapshot.params["id"],
    title: '',
    body: '',
    published: false,
  }
  message = '';

  constructor(private forumService: ForumService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    if (!this.viewModel) {
      this.message = '';
      this.forumService.getForumbyID(this.route.snapshot.params["id"]).subscribe(
        {
          next: (data: any) => {
            this.currentForum = data;
          },
          error: (e) => {
            console.error(e)
          }
        })

    }


  }

  setCurrentForum(forum: any) {
    this.currentForum = forum
    console.log(this.currentForum, "current post")
  }

  getForum(id: number): void {
    this.forumService.getForumbyID(id)
      .subscribe({
        next: (data) => {
          this.setCurrentForum(data)
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  updatePublished(status: boolean): void {
    console.log(this.currentForum)
    const data = {
      title: this.currentForum.title,
      description: this.currentForum.body,
      published: status
    };
    this.message = '';
    this.forumService.updateForum(this.route.snapshot.params["id"], data).subscribe({
      next: (res) => {
        console.log(res);
        this.currentForum.published = status;
        this.message = res.message ? res.message : 'The Forum was updated successfully!';
      },
      error: (e) => console.error(e)
    });
  }
  updateForum(id:number): void {
    console.log(this.currentForum,"update")
    this.message = '';
    this.forumService.updateForum(this.route.snapshot.params["id"], this.currentForum)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'This Post was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }
  deleteForum(): void {
    this.forumService.deleteForum(this.route.snapshot.params["id"])
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/forum']);
        },
        error: (e) => console.error(e)
      });
  }
}
